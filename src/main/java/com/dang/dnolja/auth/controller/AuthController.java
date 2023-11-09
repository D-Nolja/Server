package com.dang.dnolja.auth.controller;

import com.dang.dnolja.event.UserRegistrationEvent;
import com.dang.dnolja.response.CommonResponse;
import com.dang.dnolja.response.ResultCode;
import com.dang.dnolja.auth.dto.request.JoinReqDto;
import com.dang.dnolja.auth.dto.request.LoginRequestDto;
import com.dang.dnolja.auth.dto.response.LoginResponseDto;
import com.dang.dnolja.user.model.dto.UserDto;
import com.dang.dnolja.user.model.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    @PostMapping("/join")
    public CommonResponse<String> join(@RequestBody @Valid JoinReqDto user, BindingResult bindingResult){
        log.debug("[UserController join] request :: {}", user);

        UserDto joinResult = userService.join(user);
        eventPublisher.publishEvent(new UserRegistrationEvent(joinResult));
        return new CommonResponse<String>(user.getUsername()+"의 회원가입이 완료되었습니다.");
    }

}
