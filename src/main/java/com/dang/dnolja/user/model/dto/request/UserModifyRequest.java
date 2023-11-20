package com.dang.dnolja.user.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
@Data
@AllArgsConstructor
public class UserModifyRequest {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,20}$", message="한글/영문/숫자로만 구성된 2~20자 이내의 닉네임을 사용해주세요")
    private String username;

    @NotEmpty
    private String img;

}
