package com.oussama.hunters_league.web.rest.species;

import com.oussama.hunters_league.domain.Species;
import com.oussama.hunters_league.service.impl.SpeciesServiceImpl;
import com.oussama.hunters_league.web.vm.ResponseSpeciesVM;
import com.oussama.hunters_league.web.vm.mapper.species.AddSpecies;
import com.oussama.hunters_league.web.vm.species.AddSpeciesVM;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/species")
public class SpeciesController {

    private final SpeciesServiceImpl speciesServiceimpl;
    private final AddSpecies addSpecies;


    public SpeciesController(SpeciesServiceImpl speciesServiceimpl, AddSpecies addSpecies){
        this.speciesServiceimpl = speciesServiceimpl;
        this.addSpecies=addSpecies;
    }

    @Transactional
    @PostMapping("/addSpecies")
    public ResponseEntity<Map<String,Object>> addSpecies(@RequestBody @Valid AddSpeciesVM addSpeciesVM){
        Species species = addSpecies.toSpecies(addSpeciesVM);
        Species spec=speciesServiceimpl.addSpecies(species);
        ResponseSpeciesVM responseSpeciesVM= addSpecies.toResponseSpeciesVM(spec);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Species added successfully");
        response.put("data", responseSpeciesVM);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
