package com.oussama.hunters_league.exception.Participation;

public class ParticipationAlreadyExistsException extends RuntimeException{
    public ParticipationAlreadyExistsException(String msg){
        super(msg);
    }
}
