package com.dang.dnolja.user.controller;

import com.dang.dnolja.auth.details.CustomUserDetail;
import com.dang.dnolja.global.response.CommonResponse;
import com.dang.dnolja.user.model.dto.UserDto;
import com.dang.dnolja.user.model.dto.request.UserModifyRequest;
import com.dang.dnolja.user.model.dto.response.UserResDto;
import com.dang.dnolja.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping
    public CommonResponse<String> modifyUser(Authentication auth, @RequestBody UserModifyRequest request){
        log.debug("[] details::{}, principals :{}", auth.getDetails(), auth.getPrincipal());
        CustomUserDetail detail = (CustomUserDetail) auth.getPrincipal();
        UserDto user = detail.getUser();

        userService.modify(user.getId(), request.getUsername(), request.getImg());

        return new CommonResponse<String>("수정 성공");
    }


    @GetMapping
    public CommonResponse<UserResDto> getUser(Authentication auth){

        CustomUserDetail detail = (CustomUserDetail) auth.getPrincipal();
        UserDto user = detail.getUser();

        return new CommonResponse<>(UserResDto
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .img(user.getProfile())
                .build());
    }







}
