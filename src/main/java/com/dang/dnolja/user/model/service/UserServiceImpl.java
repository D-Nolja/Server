package com.dang.dnolja.user.model.service;

import com.dang.dnolja.global.Exception.DuplicatedEmailException;
import com.dang.dnolja.auth.dto.request.JoinReqDto;
import com.dang.dnolja.user.model.dto.UserDto;
import com.dang.dnolja.user.model.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDto join(JoinReqDto req) {
        UserDto user = new UserDto();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole("ROLE_USER");
        log.debug("[UserServiceImpl UserDto] saveUser :: {}", user);

        if(userMapper.findByEmail(user.getEmail())!=null){
            throw new DuplicatedEmailException("이미 존재하는 이메일 계정입니다.");
        }

        userMapper.join(user);

        return user;
    }

    @Override
    public UserDto findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public void updateVerified(long userId) {
        userMapper.updateVerified(userId);
    }

    @Override
    public void modify(Long id, String username, String img){
        Map<String, Object> params = new HashMap<>();

        params.put("userId", id);
        params.put("username", username);
        params.put("img", img);
        userMapper.modify(params);

    }
}
