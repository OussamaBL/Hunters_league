package com.oussama.hunters_league.service.impl;

import com.oussama.hunters_league.domain.Enum.SpeciesType;
import com.oussama.hunters_league.domain.Species;
import com.oussama.hunters_league.domain.User;
import com.oussama.hunters_league.exception.NullVarException;
import com.oussama.hunters_league.exception.Species.SpeciesAlreadyExistException;
import com.oussama.hunters_league.exception.User.UserAlreadyExistException;
import com.oussama.hunters_league.repository.SpeciesRepository;
import com.oussama.hunters_league.service.SpeciesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Service
public class SpeciesServiceImpl implements SpeciesService {
    private final SpeciesRepository speciesRepository;
    public SpeciesServiceImpl(SpeciesRepository speciesRepository){
        this.speciesRepository=speciesRepository;
    }
    @Override
    public Species addSpecies(Species species) {
        Optional<Species> spec=speciesRepository.findByName(species.getName());
        spec.ifPresent(s -> {throw new SpeciesAlreadyExistException("Name already exist");});
        return speciesRepository.save(species);

    }

    @Override
    public Species updateSpecies(Species species) {
        if(species.getId()==null) throw new NullVarException("id is null");
        Optional<Species> spec=speciesRepository.findById(species.getId());
        spec.orElseThrow(() -> new SpeciesAlreadyExistException("Species id not exist"));
        Species existingSpecies=spec.get();

        if (species.getName() != null && !species.getName().equals(existingSpecies.getName())) {
            speciesRepository.findByName(species.getName()).ifPresent(u -> {
                throw new SpeciesAlreadyExistException("Name already exists");
            });
            existingSpecies.setName(species.getName());
        }


        if (species.getCategory() != null) existingSpecies.setCategory(species.getCategory());
        if (species.getDifficulty() != null) existingSpecies.setDifficulty(species.getDifficulty());
        if (species.getMinimumWeight() != null) existingSpecies.setMinimumWeight(species.getMinimumWeight());
        if (species.getPoints() != null) existingSpecies.setPoints(species.getPoints());

        return speciesRepository.save(existingSpecies);
    }

    @Override
    public void deletespecies(UUID id) {

    }

    @Override
    public Page<Species> getSpeciesByCategory(SpeciesType speciesType, int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return speciesRepository.getAllByCategory(speciesType,pageable);
    }
    @Override
    public void deleteSpecies(UUID id) {
        speciesRepository.deleteById(id);
    }
}
