package com.dang.dnolja.global.Exception;

import lombok.Getter;

public class CrawlingException extends RuntimeException{


    public CrawlingException(String message) {
        super(message);
    }
}
