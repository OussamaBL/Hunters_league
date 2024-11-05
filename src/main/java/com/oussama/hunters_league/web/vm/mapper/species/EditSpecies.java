package com.oussama.hunters_league.web.vm.mapper.species;

import com.oussama.hunters_league.domain.Species;
import com.oussama.hunters_league.web.vm.ResponseSpeciesVM;
import com.oussama.hunters_league.web.vm.species.AddSpeciesVM;
import com.oussama.hunters_league.web.vm.species.EditSpeciesVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EditSpecies {
    ResponseSpeciesVM toResponseSpeciesVM(Species species);
    Species toSpecies(EditSpeciesVM editSpeciesVM);
}
