package com.oussama.hunters_league.web.vm.hunt;

import com.oussama.hunters_league.domain.Participation;
import com.oussama.hunters_league.domain.Species;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddHuntVM {

    @NotNull(message = "Species is required.")
    private UUID species;

    @NotNull(message = "weight is required.")
    private Double weight;

    @NotNull(message = "participation is required.")
    private UUID participation;
}
