package com.oussama.hunters_league.web.vm;

import com.oussama.hunters_league.domain.Enum.Difficulty;
import com.oussama.hunters_league.domain.Enum.SpeciesType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ResponseSpeciesVM {

    private UUID id;
    private String name;
    private SpeciesType category;
    private Double minimumWeight;
    private Difficulty difficulty;
    private Integer points;
}
