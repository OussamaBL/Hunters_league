package com.oussama.hunters_league.service.impl;

import com.oussama.hunters_league.domain.Species;
import com.oussama.hunters_league.exception.Species.SpeciesAlreadyExistException;
import com.oussama.hunters_league.repository.SpeciesRepository;
import com.oussama.hunters_league.service.SpeciesService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SpeciesServiceImpl implements SpeciesService {
    private final SpeciesRepository speciesInterface;
    public SpeciesServiceImpl(SpeciesRepository speciesInterface){
        this.speciesInterface=speciesInterface;
    }
    @Override
    public Species addSpecies(Species species) {
        Optional<Species> spec=speciesInterface.findByName(species.getName());
        spec.ifPresent(s -> {throw new SpeciesAlreadyExistException("Name already exist");});
        return speciesInterface.save(species);

    }

    @Override
    public Species updateSpecies(Species species) {
        return null;
    }

    @Override
    public void deletespecies(UUID id) {

    }
}
