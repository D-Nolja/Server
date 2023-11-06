package com.dang.dnolja.email.controller;

import com.dang.dnolja.email.model.service.EmailVerificationService;
import com.dang.dnolja.response.CommonResponse;
import com.dang.dnolja.response.ResultCode;
import com.dang.dnolja.user.model.dto.UserDto;
import com.dang.dnolja.user.model.mapper.UserMapper;
import com.dang.dnolja.user.model.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@Slf4j
@RestController
@AllArgsConstructor
public class EmailVerificationController {

    private final EmailVerificationService emailVerificationService;
    private final UserService userService;


    @GetMapping("/verify/email")
    public CommonResponse verifyEmail(@RequestParam String id){


        log.debug("[EmailVerificationController verifyEmail] id :: {}", id);
        //id값을 이용하여 email을 조회한다.
        String email = emailVerificationService.getEmailFromVerificationId(id);


        //user가 존재하면 verified를 true로 저장하고 인증 완료 응답을 준다.
        if(email!=null){

            UserDto user = userService.findByEmail(email);
            log.debug("[EmailVerificationController verifyEmail] user ::{}",user);
            userService.updateVerified(user.getId());

            return new CommonResponse<String>(user.getUsername()+"님의 이메일 인증이 완료되었습니다.");
        }

        throw new IllegalArgumentException("이메일 인증 url이 잘못되었습니다.");
    }


}