package com.oussama.hunters_league.web.vm.mapper.species;

import com.oussama.hunters_league.domain.Species;
import com.oussama.hunters_league.web.vm.species.ResponseSpeciesVM;
import com.oussama.hunters_league.web.vm.species.AddSpeciesVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddSpecies {
    ResponseSpeciesVM toResponseSpeciesVM(Species species);
    Species toSpecies(AddSpeciesVM addSpeciesVM);
}
