package com.oussama.hunters_league.web.vm.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PodiumDTO {
    private UUID participation_id;
    private String username;
    private double score;
}
