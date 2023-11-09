package com.dang.dnolja.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class JwtExceptionResponse {

    String exception;

    HttpStatus status;


}
