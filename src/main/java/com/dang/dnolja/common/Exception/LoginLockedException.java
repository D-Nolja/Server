package com.dang.dnolja.common.Exception;

public class LoginLockedException extends RuntimeException{

    public LoginLockedException(String message) {
        super(message);
    }
}
