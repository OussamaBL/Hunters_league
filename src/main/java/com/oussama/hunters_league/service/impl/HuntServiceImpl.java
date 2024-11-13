package com.oussama.hunters_league.service.impl;

import com.oussama.hunters_league.domain.Enum.Difficulty;
import com.oussama.hunters_league.domain.Enum.SpeciesType;
import com.oussama.hunters_league.domain.Hunt;
import com.oussama.hunters_league.domain.Participation;
import com.oussama.hunters_league.domain.Species;
import com.oussama.hunters_league.exception.Hunt.HuntCategoryInvalidException;
import com.oussama.hunters_league.exception.Hunt.HuntWeightInvalidException;
import com.oussama.hunters_league.exception.Participation.ParticipationNotFoundException;
import com.oussama.hunters_league.exception.Species.SpeciesNotExistsException;
import com.oussama.hunters_league.repository.HuntRepository;
import com.oussama.hunters_league.repository.ParticipationRepository;
import com.oussama.hunters_league.repository.SpeciesRepository;
import com.oussama.hunters_league.service.HuntService;
import com.oussama.hunters_league.web.vm.hunt.AddHuntVM;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HuntServiceImpl implements HuntService {
    private final HuntRepository huntRepository;
    private final SpeciesRepository speciesRepository;
    private final ParticipationRepository participationRepository;
    public HuntServiceImpl(HuntRepository huntRepository,SpeciesRepository speciesRepository,ParticipationRepository participationRepository){
        this.huntRepository=huntRepository;
        this.speciesRepository=speciesRepository;
        this.participationRepository=participationRepository;
    }
    @Override
    public Hunt addHunt(AddHuntVM addHuntVM) {
        Hunt hunt=new Hunt();

        Optional<Species> spec=speciesRepository.findById(addHuntVM.getSpecies());
        if(spec.isEmpty()) throw new SpeciesNotExistsException("Species not exist");
        Species species=spec.get();
        hunt.setSpecies(species);

        Optional<Participation> prtcp=participationRepository.findById(addHuntVM.getParticipation());
        if(prtcp.isEmpty()) throw new ParticipationNotFoundException("Participation not exist");
        Participation participation=prtcp.get();
        hunt.setParticipation(participation);

        if(species.getCategory()!=participation.getCompetition().getSpeciesType()) throw new HuntCategoryInvalidException("Category of species is different than competition type");
        if(addHuntVM.getWeight()<species.getMinimumWeight()) throw new HuntWeightInvalidException("Weight of hunt greater than minimun weight of species");
        hunt.setWeight(addHuntVM.getWeight());
        Hunt savedHunt=huntRepository.save(hunt);
        updateScoreParticipation(savedHunt);

        return savedHunt;
    }
    public void updateScoreParticipation(Hunt hunt){
        Species species=hunt.getSpecies();
        Participation participation=hunt.getParticipation();
        int facteur_species=species.getCategory().getValue();
        int facteur_difficulty=species.getDifficulty().getValue();

        Double score= (species.getPoints())+(hunt.getWeight()*facteur_species)* facteur_difficulty;
        participation.setScore(score);
        participationRepository.save(participation);

    }
}
