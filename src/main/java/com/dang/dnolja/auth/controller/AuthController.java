package com.dang.dnolja.auth.controller;

import com.dang.dnolja.auth.service.AuthService;
import com.dang.dnolja.event.UserRegistrationEvent;
import com.dang.dnolja.response.CommonResponse;
import com.dang.dnolja.response.ResultCode;
import com.dang.dnolja.user.model.dto.JoinReqDto;
import com.dang.dnolja.user.model.dto.LoginRequestDto;
import com.dang.dnolja.user.model.dto.LoginResponseDto;
import com.dang.dnolja.user.model.dto.UserDto;
import com.dang.dnolja.user.model.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
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
    private final AuthService authService;

    @GetMapping("/denied")
    public ResponseEntity<CommonResponse<?>> accessDenied(){
        return new ResponseEntity<CommonResponse<?>>(new CommonResponse<String>(ResultCode.ERROR, new Exception("로그인을 진행해주세요")),HttpStatus.FORBIDDEN );
    }

    @PostMapping("/join")
    public CommonResponse<String> join(@RequestBody @Valid JoinReqDto user, BindingResult bindingResult){
        log.debug("[UserController join] request :: {}", user);

        UserDto joinResult = userService.join(user);
        eventPublisher.publishEvent(new UserRegistrationEvent(joinResult));
        return new CommonResponse<String>(user.getUsername()+"의 회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public CommonResponse<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto user, BindingResult bindingResult){
        log.debug("[] request ::{}", user);
        LoginResponseDto result = authService.loginMember(user);
        return new CommonResponse<LoginResponseDto>(result);
    }
}
