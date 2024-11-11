package com.oussama.hunters_league.web.vm.species;

import com.oussama.hunters_league.domain.Enum.Difficulty;
import com.oussama.hunters_league.domain.Enum.SpeciesType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddSpeciesVM {

    @NotBlank(message = "Species name is required.")
    @Size(max = 50, message = "Species name cannot exceed 50 characters.")
    private String name;

    @NotNull(message = "Species category is required.")
    private SpeciesType category;

    @NotNull(message = "Minimum weight is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Minimum weight must be greater than 0.")
    private Double minimumWeight;

    @NotNull(message = "Difficulty level is required.")
    private Difficulty difficulty;

    @NotNull(message = "Points are required.")
    @Min(value = 0, message = "Points must be 0 or greater.")
    private Integer points;
}
