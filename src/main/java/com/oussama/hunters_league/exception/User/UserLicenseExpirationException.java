package com.oussama.hunters_league.exception.User;

public class UserLicenseExpirationException extends RuntimeException{
    public UserLicenseExpirationException(String msg){
        super(msg);
    }
}
