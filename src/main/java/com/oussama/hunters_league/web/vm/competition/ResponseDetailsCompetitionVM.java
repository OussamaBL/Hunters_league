package com.oussama.hunters_league.web.vm.competition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetailsCompetitionVM {
    private LocalDateTime date;
    private String lieu;
    private int nombreParticipants;
}
