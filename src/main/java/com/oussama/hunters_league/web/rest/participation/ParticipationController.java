package com.oussama.hunters_league.web.rest.participation;

import com.oussama.hunters_league.domain.Participation;
import com.oussama.hunters_league.service.impl.ParticipationServiceImpl;
import com.oussama.hunters_league.utils.JwtUtil;
import com.oussama.hunters_league.web.vm.result.HistoryResultDTO;
import com.oussama.hunters_league.web.vm.result.ParticipationResulVM;
import com.oussama.hunters_league.web.vm.participation.ParticipationInCompetitionVM;
import com.oussama.hunters_league.web.vm.result.PodiumDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/participation")
public class ParticipationController {

    private final ParticipationServiceImpl participationServiceImpl;
    private final JwtUtil jwtUtil;
    public ParticipationController(ParticipationServiceImpl participationServiceImpl,JwtUtil jwtUtil){
        this.participationServiceImpl=participationServiceImpl;
        this.jwtUtil=jwtUtil;

    }

    @PostMapping("/participateInCompetition")
    public ResponseEntity<String> participateInCompetition(@RequestBody @Valid ParticipationInCompetitionVM participationInCompetitionVM){
        /*jwtUtil.validate_auth();*/
        Participation participation=participationServiceImpl.participationInCompetition(participationInCompetitionVM.getUserId(),participationInCompetitionVM.getCompetitionId());
        return ResponseEntity.ok("Participation successful");

    }

    @GetMapping("/results/{userId}")
    public List<ParticipationResulVM> getUserCompetitionResults(@PathVariable UUID userId) {
        /*jwtUtil.validate_auth();*/
        return participationServiceImpl.getUserCompetitionResults(userId);
    }

    @GetMapping("/podiumResult/{competitionId}")
    public List<PodiumDTO> getCompetitionPodium(@PathVariable UUID competitionId) {
        /*jwtUtil.validate_auth();*/
        return participationServiceImpl.getCompetitionPodium(competitionId);
    }

    @GetMapping("/competitionHistory/{user_id}")
    public List<HistoryResultDTO> getAppUserCompetitionHistory(@PathVariable UUID user_id) {
        /*jwtUtil.validate_auth();*/
        return participationServiceImpl.getUserCompetitionHistory(user_id);
    }

}
