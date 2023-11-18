package com.dang.dnolja.global.Exception;

public class LoginLockedException extends RuntimeException{

    public LoginLockedException(String message) {
        super(message);
    }
}
