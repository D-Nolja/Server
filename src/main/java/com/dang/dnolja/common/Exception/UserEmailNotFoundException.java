package com.dang.dnolja.common.Exception;

public class UserEmailNotFoundException extends RuntimeException{
    public UserEmailNotFoundException() {
        super("이메일이 존재하지 않습니다.");
    }
}
