package com.oussama.hunters_league.web.vm.hunt;

import com.oussama.hunters_league.domain.Participation;
import com.oussama.hunters_league.domain.Species;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHuntVM {
    private UUID id;

    private UUID species_id;

    private Double weight;

    private UUID participation_id;
}
