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
import com.oussama.hunters_league.web.vm.result.HistoryResultDTO;
import com.oussama.hunters_league.web.vm.result.ParticipationResulVM;
import com.oussama.hunters_league.web.vm.result.PodiumDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public List<ParticipationResulVM> getUserCompetitionResults(UUID id) {

        if(!userRepository.existsById(id)) throw new UserNotFoundException("user not exist");
        List<Participation> participationList=participationRepository.findAllByUser_Id(id);
        return participationList.stream()
                .map(participation ->
                    new ParticipationResulVM(
                            participation.getCompetition().getId(),
                            participation.getCompetition().getCode(),
                            participation.getCompetition().getLocation(),
                            participation.getCompetition().getDate(),
                            participation.getScore()
                    )
                ).collect(Collectors.toList());

    }

    @Override
    public List<PodiumDTO> getCompetitionPodium(UUID competition_id) {
        if(!competitionRepository.existsById(competition_id)) throw new CompetitionNotFoundException("competition not exist");
        List<Participation> participationList= participationRepository.findTop3ByCompetition_IdOrderByScoreDesc(competition_id);
         return participationList.stream().map(participation ->
            new PodiumDTO(
                    participation.getId(),
                    participation.getUser().getUsername(),
                    participation.getScore()

            )
        ).collect(Collectors.toList());
    }

    @Override
    public List<HistoryResultDTO> getUserCompetitionHistory(UUID user_id) {
        if(!userRepository.existsById(user_id)) throw new UserNotFoundException("user not exist");
        List<Participation> participationList=participationRepository.findAllByUser_IdAndCompetition_DateBeforeOrderByCompetition_DateDesc(user_id,LocalDateTime.now());
        return participationList.stream().map(
                participation -> new HistoryResultDTO(
                        participation.getCompetition().getId(),
                        participation.getCompetition().getDate(),
                        participation.getCompetition().getLocation(),
                        participation.getScore(),
                        this.getRankParticipation(participation)
                )
        ).collect(Collectors.toList());
    }
    public int getRankParticipation(Participation participation){
        List<Participation> participationList =participation.getCompetition().getParticipations();
        participationList.stream().sorted((p1,p2)->
            Double.compare(p1.getScore(),p2.getScore())
        ).collect(Collectors.toList());
        for (int i=0;i<participationList.size();i++) {
            if (participationList.get(i).getId()==participation.getId()) return i+1;
        }
        return 0;
    }
}
