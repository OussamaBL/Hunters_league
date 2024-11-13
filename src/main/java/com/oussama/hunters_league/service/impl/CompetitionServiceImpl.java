package com.oussama.hunters_league.service.impl;

import com.oussama.hunters_league.domain.Competition;
import com.oussama.hunters_league.exception.Competition.CompetitionAlreadyExistException;
import com.oussama.hunters_league.exception.Competition.CompetitionDateException;
import com.oussama.hunters_league.exception.Competition.CompetitionInvalidException;
import com.oussama.hunters_league.repository.CompetitionRepository;
import com.oussama.hunters_league.service.CompetitionService;
import com.oussama.hunters_league.utils.DatesUtil;
import com.oussama.hunters_league.web.vm.competition.ResponseDetailsCompetitionVM;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;

    private final DatesUtil datesUtil;
    public CompetitionServiceImpl(CompetitionRepository competitionRepository,DatesUtil datesUtil){
        this.competitionRepository=competitionRepository;
        this.datesUtil=datesUtil;
    }

    @Override
    public Competition addCompetition(Competition competition) {
        Optional<Competition> comp=competitionRepository.findByCode(competition.getCode());
        if(comp.isPresent()) throw new CompetitionAlreadyExistException("Code already exists");
        if(competition.getDate().isBefore(LocalDateTime.now())) throw new CompetitionInvalidException("Date should be after localdate");
        if(competition.getMinParticipants()>=competition.getMaxParticipants()) throw new CompetitionInvalidException("max participant should be greater then min participant");
        LocalDateTime date1=DatesUtil.getStartOfWeek(competition.getDate());
        LocalDateTime date2=DatesUtil.getEndOfWeek(competition.getDate());
        if(competitionRepository.existsByDateBetween(date1,date2)) throw new CompetitionDateException("Already create competition in this week");
        return competitionRepository.save(competition);
    }

    @Override
    public Competition updateCompetition(Competition competition) {
        return null;
    }

    @Override
    public void deleteCompetition(UUID id) {

    }

    @Override
    public ResponseDetailsCompetitionVM getCompetitionDetails(UUID id) {
        Optional<Competition> competition=competitionRepository.findById(id);
        if(competition.isEmpty()) throw new CompetitionAlreadyExistException("competition not found");
        Competition cmp=competition.get();
        int cpt=cmp.getParticipations()!=null ? cmp.getParticipations().size() : 0;
        return new ResponseDetailsCompetitionVM(cmp.getDate(),cmp.getLocation(),cpt);
    }

    @Scheduled(fixedRate = 3600000)
    @Override
    public void closeParticipation() {
        LocalDateTime date=LocalDateTime.now().plusHours(24);
        List<Competition> competitionList=competitionRepository.findAllByOpenRegistrationTrueAndDateBefore(date);
        competitionList.forEach((competition -> { competition.setOpenRegistration(false); }));
        competitionRepository.saveAll(competitionList);
    }
}
