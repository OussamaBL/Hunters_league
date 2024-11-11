package com.oussama.hunters_league.service;

import com.oussama.hunters_league.domain.Participation;

import java.util.UUID;

public interface ParticipationService {
    Participation participationInCompetition(UUID id_user,UUID id_competition);
}
