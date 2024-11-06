package com.oussama.hunters_league.service;

import com.oussama.hunters_league.domain.Enum.SpeciesType;
import com.oussama.hunters_league.domain.Species;
import org.springframework.data.domain.Page;


import java.util.UUID;

public interface SpeciesService {

    Species addSpecies(Species species);
    Species updateSpecies(Species species);
    void deletespecies(UUID id);
    Page<Species> getSpeciesByCategory(SpeciesType speciesType, int page, int size);
}
