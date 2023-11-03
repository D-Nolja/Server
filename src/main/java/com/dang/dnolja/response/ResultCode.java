package com.dang.dnolja.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    SUCCESS("0000", "SUCCESS"),
    ERROR("9999", "ERROR");

    private String code;
    private String message;
}
