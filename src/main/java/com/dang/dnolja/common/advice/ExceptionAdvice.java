package com.dang.dnolja.common.advice;

import com.dang.dnolja.response.CommonResponse;
import com.dang.dnolja.response.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public CommonResponse<?> illegalArgumentException(IllegalArgumentException e){

        return new CommonResponse(ResultCode.ERROR, e);
    }
}
