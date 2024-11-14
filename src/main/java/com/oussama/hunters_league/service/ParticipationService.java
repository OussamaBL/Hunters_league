package com.oussama.hunters_league.service;

import com.oussama.hunters_league.domain.Participation;
import com.oussama.hunters_league.web.vm.competition.ParticipationResulVM;

import java.util.List;
import java.util.UUID;

public interface ParticipationService {
    Participation participationInCompetition(UUID id_user,UUID id_competition);
    List<ParticipationResulVM> getUserCompetitionResults(UUID id);
}
