package com.dang.dnolja.user.controller;

import com.dang.dnolja.response.CommonResponse;
import com.dang.dnolja.response.ResultCode;
import com.dang.dnolja.user.model.dto.UserDto;
import com.dang.dnolja.user.model.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("user")
    public CommonResponse<String> join(@RequestBody UserDto user){
        log.debug("[UserController join] request :: {}", user);
//        userService.join(user);

        return new CommonResponse(user.getUsername()+"의 회원가입이 완료되었습니다.");
    }

}
