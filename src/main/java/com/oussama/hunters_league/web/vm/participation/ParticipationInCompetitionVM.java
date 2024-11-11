package com.oussama.hunters_league.web.vm.participation;

import jakarta.validation.constraints.NotBlank;
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
public class ParticipationInCompetitionVM {

    @NotNull(message = "User is required")
    private UUID userId;

    @NotNull(message = "Competition is required")
    private UUID competitionId;
}
