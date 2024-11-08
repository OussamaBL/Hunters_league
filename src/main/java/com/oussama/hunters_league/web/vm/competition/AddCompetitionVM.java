package com.oussama.hunters_league.web.vm.competition;

import com.oussama.hunters_league.domain.Enum.SpeciesType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCompetitionVM {

    @NotBlank(message = "Code is required.")
    @Pattern(regexp = "^[A-Za-z]+_\\d{4}$", message = "Le code de la compétition doit respecter le pattern: lieux_date (ex: Paris_2024)")
    private String code;

    @NotNull(message = "Location is required.")
    private String location;

    @NotNull(message = "Date is required.")
    @Future(message = "date should be in future")
    private LocalDateTime date;

    @NotNull(message = "speciesType is required.")
    @Enumerated(EnumType.STRING)
    private SpeciesType speciesType;

    @NotNull(message = "minParticipants are required.")
    @Min(value = 0, message = "minParticipants must be 0 or greater.")
    private Integer minParticipants;

    @NotNull(message = "maxParticipants are required.")
    @Min(value = 0, message = "maxParticipants must be 0 or greater.")
    private Integer maxParticipants;

    @NotNull(message = "openRegistration (true or false) are required.")
    private Boolean openRegistration;
}
