package com.dang.dnolja.user.model.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResDto {
    long id;
    String email;
    String username;
    String img;

}
