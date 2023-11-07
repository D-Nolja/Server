package com.dang.dnolja.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
public class LoginRequestDto {

    @NotEmpty
    @Pattern(regexp =  "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", message = "유효한 이메일 양식이 아닙니다.")
    private String email;

    @NotEmpty
    private String password;


}
