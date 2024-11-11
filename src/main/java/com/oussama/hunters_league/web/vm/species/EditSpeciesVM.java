package com.oussama.hunters_league.web.vm.species;

import com.oussama.hunters_league.domain.Enum.Difficulty;
import com.oussama.hunters_league.domain.Enum.SpeciesType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditSpeciesVM {
    @Nullable
    @Size(max = 50, message = "Species name cannot exceed 50 characters.")
    private String name;

    @Nullable
    private SpeciesType category;

    @Nullable
    @DecimalMin(value = "0.0", inclusive = false, message = "Minimum weight must be greater than 0.")
    private Double minimumWeight;

    @Nullable
    private Difficulty difficulty;

    @Nullable
    @Min(value = 0, message = "Points must be 0 or greater.")
    private Integer points;
}
