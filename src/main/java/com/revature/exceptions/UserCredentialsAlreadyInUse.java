package com.revature.exceptions;

public class UserCredentialsAlreadyInUse extends RuntimeException{
    public UserCredentialsAlreadyInUse(String arg0) {
        super(arg0);
    }
}
