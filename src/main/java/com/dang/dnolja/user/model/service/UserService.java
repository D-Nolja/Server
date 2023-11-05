package com.dang.dnolja.user.model.service;

import com.dang.dnolja.user.model.dto.JoinReqDto;
import com.dang.dnolja.user.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;


public interface UserService {



    public UserDto join(JoinReqDto req);

    public UserDto findByEmail(String email);

    public void updateVerified(long userId);
}
