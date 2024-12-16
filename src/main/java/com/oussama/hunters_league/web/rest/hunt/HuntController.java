package com.oussama.hunters_league.web.rest.hunt;

import com.oussama.hunters_league.domain.Hunt;
import com.oussama.hunters_league.service.impl.HuntServiceImpl;
import com.oussama.hunters_league.web.vm.hunt.AddHuntVM;
import com.oussama.hunters_league.web.vm.hunt.ResponseHuntVM;
import com.oussama.hunters_league.web.vm.mapper.hunt.AddHuntMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/hunt")
public class HuntController {

    private final AddHuntMapper addHuntMapper;
    private final HuntServiceImpl huntServiceImpl;
    public HuntController(AddHuntMapper addHuntMapper,HuntServiceImpl huntServiceImpl){
        this.addHuntMapper=addHuntMapper;
        this.huntServiceImpl=huntServiceImpl;
    }

    @PostMapping("/addHunt")
    @PreAuthorize("hasRole('ADMIN') or hasRole('JURY')")
    public ResponseEntity<Map<String,Object>> addHunt(@RequestBody @Valid AddHuntVM addHuntVM){
        Hunt hunt1=huntServiceImpl.addHunt(addHuntVM);
        ResponseHuntVM responseHuntVM=addHuntMapper.toResponseHuntVM(hunt1);
        Map<String,Object> response=new HashMap<>();
        response.put("message","Hunt added succesfuly");
        response.put("data",responseHuntVM);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
