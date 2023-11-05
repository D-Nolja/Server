package com.dang.dnolja.user.model.service;

import com.dang.dnolja.user.model.dto.JoinReqDto;
import com.dang.dnolja.user.model.dto.UserDto;
import com.dang.dnolja.user.model.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto join(JoinReqDto req) {
        UserDto user = new UserDto();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(req.getPassword()));
        user.setRoles("USER");
//        userMapper.join(user);

        return user;
    }
}
