package com.oussama.hunters_league.web.vm.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationResulVM {
    private UUID competitionId;
    private String competitionCode;
    private String location;
    private LocalDateTime date;
    private Double score;
}
