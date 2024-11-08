package com.oussama.hunters_league.web.rest.competition;

import com.oussama.hunters_league.domain.Competition;
import com.oussama.hunters_league.service.impl.CompetitionServiceImpl;
import com.oussama.hunters_league.web.vm.competition.ResponseCompetitionVM;
import com.oussama.hunters_league.web.vm.competition.AddCompetitionVM;
import com.oussama.hunters_league.web.vm.competition.ResponseDetailsCompetitionVM;
import com.oussama.hunters_league.web.vm.mapper.competition.AddCompetition;
import com.oussama.hunters_league.web.vm.mapper.competition.DetailsCompetition;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/competition")
public class CompetitionController {
    private final CompetitionServiceImpl competitionServiceImpl;
    private final AddCompetition addCompetition;
    private final DetailsCompetition detailsCompetition;

    public CompetitionController(CompetitionServiceImpl competitionServiceImpl,AddCompetition addCompetition,DetailsCompetition detailsCompetition){
        this.competitionServiceImpl=competitionServiceImpl;
        this.addCompetition=addCompetition;
        this.detailsCompetition=detailsCompetition;
    }
    @PostMapping("/addCompetition")
    public ResponseEntity<Map<String,Object>> save(@RequestBody @Valid AddCompetitionVM competitionVm){
        Competition competition = addCompetition.toCompetition(competitionVm);
        Competition savedCompetition = competitionServiceImpl.addCompetition(competition);
        ResponseCompetitionVM competitionResponseVM = addCompetition.toResponseCompetitionVM(savedCompetition);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Competition added successfully");
        response.put("data", competitionResponseVM);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @GetMapping("/getCompetitionDetails/{id}")
    public ResponseEntity<Map<String,Object>> getCompetitionDetails(@PathVariable UUID id){
        ResponseDetailsCompetitionVM responseDetailsCompetitionVM=competitionServiceImpl.getCompetitionDetails(id);
        Map<String, Object> response = new HashMap<>();
        response.put("data", responseDetailsCompetitionVM);
        return new ResponseEntity<>(response , HttpStatus.OK);

    }
}
