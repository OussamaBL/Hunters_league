package com.oussama.hunters_league.service.impl;

import com.oussama.hunters_league.domain.Competition;
import com.oussama.hunters_league.domain.Enum.Role;
import com.oussama.hunters_league.domain.Participation;
import com.oussama.hunters_league.domain.User;
import com.oussama.hunters_league.exception.Competition.CompetitionDateException;
import com.oussama.hunters_league.exception.Competition.CompetitionInvalidException;
import com.oussama.hunters_league.exception.Competition.CompetitionNotFoundException;
import com.oussama.hunters_league.exception.Participation.ParticipationAlreadyExistsException;
import com.oussama.hunters_league.exception.Participation.ParticipationInvalidException;
import com.oussama.hunters_league.exception.User.UserLicenseExpirationException;
import com.oussama.hunters_league.exception.User.UserNotFoundException;
import com.oussama.hunters_league.exception.User.UserRoleException;
import com.oussama.hunters_league.repository.CompetitionRepository;
import com.oussama.hunters_league.repository.ParticipationRepository;
import com.oussama.hunters_league.repository.UserRepository;
import com.oussama.hunters_league.service.ParticipationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParticipationServiceImpl implements ParticipationService {
    private final ParticipationRepository participationRepository;
    private final UserRepository userRepository;
    private final CompetitionRepository competitionRepository;

    public ParticipationServiceImpl(ParticipationRepository participationRepository,UserRepository userRepository,CompetitionRepository competitionRepository){
        this.participationRepository=participationRepository;
        this.userRepository=userRepository;
        this.competitionRepository=competitionRepository;
    }
    @Override
    public Participation participationInCompetition(UUID id_user, UUID id_competition) {
        Optional<User> user=userRepository.findById(id_user);
        /*Validation User*/
        user.orElseThrow(()->new UserNotFoundException("User not found"));
        User us=user.get();
        if(us.getRole()!= Role.MEMBER) throw new UserRoleException("Role of user is not member");
        if(us.getLicenseExpirationDate().isBefore(LocalDateTime.now())) throw new UserLicenseExpirationException("License date Expire");

        /*Validation Competition*/
        Optional<Competition> competition=competitionRepository.findById(id_competition);
        competition.orElseThrow(()->new CompetitionNotFoundException("Competition not found"));
        Competition comp=competition.get();
        if(comp.getDate().isBefore(LocalDateTime.now())) throw new CompetitionDateException("Competition date has already passed");
        if(!comp.getOpenRegistration()) throw new CompetitionInvalidException("Registration is closed");

        /*Validation Participation*/
        if (comp.getParticipations().size()>=comp.getMaxParticipants()) throw new ParticipationInvalidException("Competition has reached the maximum number of participants");
        if(participationRepository.existsByUserAndCompetition(us,comp)) throw new ParticipationAlreadyExistsException("User are already registered for this competition");

        Participation participation=new Participation();
        participation.setUser(us);
        participation.setCompetition(comp);
        return participationRepository.save(participation);
    }
}
