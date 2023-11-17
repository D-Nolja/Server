package com.dang.dnolja.common.advice;

import com.dang.dnolja.common.Exception.DtoValidationException;
import com.dang.dnolja.common.Exception.DuplicatedEmailException;
import com.dang.dnolja.common.Exception.LoginFailureException;
import com.dang.dnolja.common.Exception.UserEmailNotFoundException;
import com.dang.dnolja.common.response.CommonResponse;
import com.dang.dnolja.common.response.ResultCode;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}


