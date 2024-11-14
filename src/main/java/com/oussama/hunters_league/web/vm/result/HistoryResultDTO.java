package com.oussama.hunters_league.web.vm.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Driver;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryResultDTO {
    private UUID competition_id;
    private LocalDateTime date;
    private String location;
    private double score;
    private int rank;

}
