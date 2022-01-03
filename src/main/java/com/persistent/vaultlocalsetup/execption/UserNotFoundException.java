package com.persistent.vaultlocalsetup.execption;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) { super(message);}
}
