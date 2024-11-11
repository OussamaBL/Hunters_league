package com.oussama.hunters_league.web.rest.participation;

import com.oussama.hunters_league.domain.Participation;
import com.oussama.hunters_league.service.impl.ParticipationServiceImpl;
import com.oussama.hunters_league.web.vm.participation.ParticipationInCompetitionVM;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
