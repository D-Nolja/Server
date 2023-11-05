package com.dang.dnolja.user.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Builder
@Data
@AllArgsConstructor
public class JoinReqDto {
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,20}$", message="한글/영문/숫자로만 구성된 2~20자 이내의 닉네임을 사용해주세요")
    private String username;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", message = "유효한 이메일 형식으로 작성해주세요")
    private String email;
    @NotEmpty
    private String password;
    private String profile;

}
