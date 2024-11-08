package com.oussama.hunters_league.exception.Competition;

public class CompetitionAlreadyExistException extends RuntimeException{
    public CompetitionAlreadyExistException(String msg){
        super(msg);
    }
}
