package com.oussama.hunters_league.web.rest.species;

import com.oussama.hunters_league.domain.Enum.SpeciesType;
import com.oussama.hunters_league.domain.Species;
import com.oussama.hunters_league.service.impl.SpeciesServiceImpl;
import com.oussama.hunters_league.utils.JwtUtil;
import com.oussama.hunters_league.web.vm.species.ResponseSpeciesVM;
import com.oussama.hunters_league.web.vm.mapper.species.AddSpecies;
import com.oussama.hunters_league.web.vm.mapper.species.EditSpecies;
import com.oussama.hunters_league.web.vm.species.AddSpeciesVM;
import com.oussama.hunters_league.web.vm.species.EditSpeciesVM;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/species")
public class SpeciesController {

    private final SpeciesServiceImpl speciesServiceimpl;
    private final AddSpecies addSpecies;
    private final EditSpecies editSpecies;
    private final JwtUtil jwtUtil;

    public SpeciesController(SpeciesServiceImpl speciesServiceimpl, AddSpecies addSpecies,EditSpecies editSpecies,JwtUtil jwtUtil){
        this.speciesServiceimpl = speciesServiceimpl;
        this.addSpecies=addSpecies;
        this.editSpecies=editSpecies;
        this.jwtUtil=jwtUtil;
    }

    @Transactional
    @PostMapping("/addSpecies")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String,Object>> addSpecies(@RequestBody @Valid AddSpeciesVM addSpeciesVM){
        Species species = addSpecies.toSpecies(addSpeciesVM);
        Species spec=speciesServiceimpl.addSpecies(species);
        ResponseSpeciesVM responseSpeciesVM= addSpecies.toResponseSpeciesVM(spec);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Species added successfully");
        response.put("data", responseSpeciesVM);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/Edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String,Object>> edit(@RequestBody @Valid EditSpeciesVM editSpeciesVM, @PathVariable UUID id){
        Species species=editSpecies.toSpecies(editSpeciesVM);
        species.setId(id);
        Species speciesUpdated=speciesServiceimpl.updateSpecies(species);

        ResponseSpeciesVM responseUserVM=editSpecies.toResponseSpeciesVM(speciesUpdated);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Species updated successfully");
        response.put("data", responseUserVM);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getSpecies")
    public ResponseEntity<Page<ResponseSpeciesVM>> getSpecies(@RequestParam SpeciesType speciesType,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size){
        /*jwtUtil.validate_auth();*/
        Page<Species> speciesPage=speciesServiceimpl.getSpeciesByCategory(speciesType,page,size);
        Page<ResponseSpeciesVM> responseSpeciesVM= speciesPage.map(species ->editSpecies.toResponseSpeciesVM(species));
        return new ResponseEntity<>(responseSpeciesVM,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteSpecies(@PathVariable UUID id) {
        speciesServiceimpl.deleteSpecies(id);
        return ResponseEntity.ok("Specy deleted successfully");
    }
}
