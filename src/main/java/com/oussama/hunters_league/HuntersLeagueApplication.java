package com.oussama.hunters_league;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HuntersLeagueApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuntersLeagueApplication.class, args);
    }

}
