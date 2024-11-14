package com.oussama.hunters_league.web.rest.participation;

import com.oussama.hunters_league.domain.Participation;
import com.oussama.hunters_league.service.impl.ParticipationServiceImpl;
import com.oussama.hunters_league.web.vm.result.ParticipationResulVM;
import com.oussama.hunters_league.web.vm.participation.ParticipationInCompetitionVM;
import com.oussama.hunters_league.web.vm.result.PodiumDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/participation")
public class ParticipationController {

    private final ParticipationServiceImpl participationServiceImpl;
    public ParticipationController(ParticipationServiceImpl participationServiceImpl){
        this.participationServiceImpl=participationServiceImpl;

    }

    @PostMapping("/participateInCompetition")
    public ResponseEntity<String> participateInCompetition(@RequestBody @Valid ParticipationInCompetitionVM participationInCompetitionVM){
        Participation participation=participationServiceImpl.participationInCompetition(participationInCompetitionVM.getUserId(),participationInCompetitionVM.getCompetitionId());
        return ResponseEntity.ok("Participation successful");

    }

    @GetMapping("/results/{userId}")
    public List<ParticipationResulVM> getUserCompetitionResults(@PathVariable UUID userId) {
        return participationServiceImpl.getUserCompetitionResults(userId);
    }

    @GetMapping("/podiumResult/{competitionId}")
    public List<PodiumDTO> getCompetitionPodium(@PathVariable UUID competitionId) {
        return participationServiceImpl.getCompetitionPodium(competitionId);
    }
}
