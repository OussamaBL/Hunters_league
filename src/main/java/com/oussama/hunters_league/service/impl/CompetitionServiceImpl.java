package com.oussama.hunters_league.service.impl;

import com.oussama.hunters_league.domain.Competition;
import com.oussama.hunters_league.exception.Competition.CompetitionAlreadyExistException;
import com.oussama.hunters_league.exception.Competition.CompetitionInvalidException;
import com.oussama.hunters_league.repository.CompetitionRepository;
import com.oussama.hunters_league.service.CompetitionService;
import com.oussama.hunters_league.web.vm.competition.ResponseDetailsCompetitionVM;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    public CompetitionServiceImpl(CompetitionRepository competitionRepository){
        this.competitionRepository=competitionRepository;
    }

    @Override
    public Competition addCompetition(Competition competition) {
        Optional<Competition> comp=competitionRepository.findByCode(competition.getCode());
        if(comp.isPresent()) throw new CompetitionAlreadyExistException("Code already exists");
        if(competition.getDate().isBefore(LocalDateTime.now())) throw new CompetitionInvalidException("Date should be after localdate");
        if(competition.getMinParticipants()>=competition.getMaxParticipants()) throw new CompetitionInvalidException("max participant should be greater then min participant");
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
}
