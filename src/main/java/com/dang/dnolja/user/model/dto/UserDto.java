package com.dang.dnolja.user.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private long id;
    private String username;
    private String email;

    private String password;
    private String roles;
    private String providerId;
    private String provider;

    private String profile;

    private String status;

    private String createdAt;
    private String modifiedAt;

}
