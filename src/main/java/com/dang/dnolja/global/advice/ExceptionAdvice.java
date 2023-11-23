package com.dang.dnolja.global.advice;

import com.dang.dnolja.global.Exception.*;
import com.dang.dnolja.global.response.CommonResponse;
import com.dang.dnolja.global.response.ResultCode;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public CommonResponse<?> invalidFormatException(){
        return new CommonResponse<>(ResultCode.ERROR, new IllegalArgumentException("json 양식이 유효하지 않습니다."));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public CommonResponse<?> illegalArgumentException(IllegalArgumentException e){
        e.printStackTrace();
        return new CommonResponse<>(ResultCode.ERROR, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DtoValidationException.class)
    public CommonResponse<?> dtoValidationException(DtoValidationException e){

        return new CommonResponse<>(ResultCode.ERROR, e, e.getErrorMap());
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicatedEmailException.class)
    public CommonResponse<?> duplicatedEmailException(DuplicatedEmailException e){
        return new CommonResponse<>(ResultCode.ERROR, e);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LoginFailureException.class)
    public CommonResponse<?> loginFailureException(LoginFailureException e){
        return new CommonResponse<>(ResultCode.ERROR, e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserEmailNotFoundException.class)
    public CommonResponse<?> userEmailNotFoundException(UserEmailNotFoundException e) {
        return new CommonResponse<>(ResultCode.ERROR, e);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BadSqlGrammarException.class)
    public CommonResponse<?> badSqlGrammerException(BadSqlGrammarException e){
        return new CommonResponse<>(ResultCode.ERROR, e);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    public CommonResponse invalidAuthorityException(InvalidAuthorityException e){
        return new CommonResponse<>(ResultCode.ERROR, e);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CrawlingException.class)
    public CommonResponse<?> crawlingException(CrawlingException e){
        Map<String, String> error = new HashMap<>();
        error.put("error", "크롤링이 실패했습니다");
        return new CommonResponse<>(ResultCode.ERROR, e, error);
    }



}


