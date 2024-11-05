package com.oussama.hunters_league.exception.Species;

public class SpeciesAlreadyExistException extends RuntimeException{
    public SpeciesAlreadyExistException(String msg){
        super(msg);
    }
}
