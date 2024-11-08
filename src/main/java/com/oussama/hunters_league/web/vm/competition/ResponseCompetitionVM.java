package com.oussama.hunters_league.web.vm.competition;

import com.oussama.hunters_league.domain.Enum.SpeciesType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ResponseCompetitionVM {
    private UUID id;

    private String code;

    private String location;

    private LocalDateTime date;

    private SpeciesType speciesType;

    private Integer minParticipants;

    private Integer maxParticipants;

    private Boolean openRegistration;
}
