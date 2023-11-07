package com.dang.dnolja.common.Exception;

public class DuplicatedEmailException extends RuntimeException{

    public DuplicatedEmailException(String message) {
        super(message);
    }
}
