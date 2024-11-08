package com.oussama.hunters_league.web.rest.competition;

import com.oussama.hunters_league.domain.Competition;
import com.oussama.hunters_league.service.impl.CompetitionServiceImpl;
import com.oussama.hunters_league.web.vm.ResponseCompetitionVM;
import com.oussama.hunters_league.web.vm.competition.AddCompetitionVM;
import com.oussama.hunters_league.web.vm.mapper.competition.AddCompetition;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/competition")
public class CompetitionController {
    private final CompetitionServiceImpl competitionServiceImpl;
    private final AddCompetition addCompetition;
    public CompetitionController(CompetitionServiceImpl competitionServiceImpl,AddCompetition addCompetition){
        this.competitionServiceImpl=competitionServiceImpl;
        this.addCompetition=addCompetition;
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
}
