package com.oussama.hunters_league.web.vm.mapper.hunt;

import com.oussama.hunters_league.domain.Hunt;
import com.oussama.hunters_league.domain.Species;
import com.oussama.hunters_league.web.vm.hunt.AddHuntVM;
import com.oussama.hunters_league.web.vm.hunt.ResponseHuntVM;
import com.oussama.hunters_league.web.vm.species.AddSpeciesVM;
import com.oussama.hunters_league.web.vm.species.ResponseSpeciesVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddHuntMapper {
    ResponseHuntVM toResponseHuntVM(Hunt hunt);
}
