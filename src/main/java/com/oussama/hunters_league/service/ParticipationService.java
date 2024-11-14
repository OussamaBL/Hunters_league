package com.oussama.hunters_league.service;

import com.oussama.hunters_league.domain.Participation;
import com.oussama.hunters_league.web.vm.result.HistoryResultDTO;
import com.oussama.hunters_league.web.vm.result.ParticipationResulVM;
import com.oussama.hunters_league.web.vm.result.PodiumDTO;

import java.util.List;
import java.util.UUID;

public interface ParticipationService {
    Participation participationInCompetition(UUID id_user,UUID id_competition);
    List<ParticipationResulVM> getUserCompetitionResults(UUID id);
    List<PodiumDTO> getCompetitionPodium(UUID competition_id);

    List<HistoryResultDTO> getUserCompetitionHistory(UUID user_id);
}
