package com.oussama.hunters_league.service;

import com.oussama.hunters_league.domain.Competition;
import com.oussama.hunters_league.web.vm.competition.ResponseDetailsCompetitionVM;

import java.util.UUID;

public interface CompetitionService {
    Competition addCompetition(Competition competition);
    Competition updateCompetition(Competition competition);
    void deleteCompetition(UUID id);

    ResponseDetailsCompetitionVM getCompetitionDetails(UUID id);
}
