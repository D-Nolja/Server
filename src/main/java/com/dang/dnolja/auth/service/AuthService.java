package com.dang.dnolja.auth.service;

import com.dang.dnolja.auth.jwt.JwtTokenProvider;
import com.dang.dnolja.common.Exception.LoginFailureException;
import com.dang.dnolja.user.model.dto.LoginRequestDto;
import com.dang.dnolja.user.model.dto.LoginResponseDto;
import com.dang.dnolja.user.model.dto.UserDto;
import com.dang.dnolja.user.model.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponseDto loginMember(LoginRequestDto requestDto) {
        UserDto member = userMapper.findByEmail(requestDto.getEmail());
        if(member == null)
            throw new IllegalArgumentException("존재하지 않는 아이디 입니다.");

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword()))
            throw new LoginFailureException("비빌먼호가 일치하지 않습니다.");

        return new LoginResponseDto(member.getId(), jwtTokenProvider.createToken(requestDto.getEmail()));

    }
}
