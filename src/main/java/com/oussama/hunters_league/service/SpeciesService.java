package com.oussama.hunters_league.service;

import com.oussama.hunters_league.domain.Species;

import java.util.UUID;

public interface SpeciesService {

    Species addSpecies(Species species);
    Species updateSpecies(Species species);
    void deletespecies(UUID id);
}
