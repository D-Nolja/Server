package com.dang.dnolja.auth.controller;

import com.dang.dnolja.global.Exception.LoginDisabledException;
import com.dang.dnolja.global.Exception.LoginLockedException;
import com.dang.dnolja.email.event.UserRegistrationEvent;
import com.dang.dnolja.global.response.CommonResponse;
import com.dang.dnolja.global.response.ResultCode;
import com.dang.dnolja.auth.dto.request.JoinReqDto;
import com.dang.dnolja.auth.dto.response.LoginResponseDto;
import com.dang.dnolja.user.model.dto.UserDto;
import com.dang.dnolja.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    @Value("${spring.jwt.access.header}")
    private String accessHeader;

    @Value("${spring.jwt.refresh.header}")
    private String refreshHeader;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    @PostMapping("/join")
    public CommonResponse<String> join(@RequestBody @Valid JoinReqDto user, BindingResult bindingResult){
        log.debug("[UserController join] request :: {}", user);

        UserDto joinResult = userService.join(user);
        eventPublisher.publishEvent(new UserRegistrationEvent(joinResult));
        return new CommonResponse<String>(user.getUsername()+"의 회원가입이 완료되었습니다.");
    }

    @GetMapping("/auth/error")
    public ResponseEntity<CommonResponse<String>> loginError(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonResponse(ResultCode.ERROR,  new IllegalArgumentException("아이디와 비밀번호를 다시 확인해주세요")));
    }

    @GetMapping("/auth/disabled")
    public ResponseEntity<CommonResponse<String>> loginDisabled(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CommonResponse<>(ResultCode.ERROR, new LoginDisabledException("회원가입 시 사용한 이메일로 인증 메일을 확인해주세요")));
    }

    @GetMapping("/auth/locked")
    public ResponseEntity<CommonResponse<String>> loginLocked(){
        return ResponseEntity.status(HttpStatus.LOCKED).body(new CommonResponse<>(ResultCode.ERROR, new LoginLockedException("로그인을 3회 연속 실패하여 계정이 잠겼습니다. 24시간 뒤 다시 시도해주세요.")));
    }

    @PostMapping("/auth/success")
    public CommonResponse<LoginResponseDto> loginSuccess(HttpServletResponse response){

        log.info("[AuthController loginSuccess] :: accessToken : {}, refreshToken : {}, response{}",response.getHeader(accessHeader), response.getHeader(refreshHeader), response );
        return new CommonResponse<>(new LoginResponseDto(response.getHeader(accessHeader), response.getHeader(refreshHeader)));
    }

}
