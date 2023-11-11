package com.dang.dnolja.auth.service;

import com.dang.dnolja.auth.details.CustomUserDetail;
import com.dang.dnolja.common.Exception.UserEmailNotFoundException;
import com.dang.dnolja.user.model.dto.UserDto;
import com.dang.dnolja.user.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UserMapper userMapper;


    @Override
    public CustomUserDetail loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto user = userMapper.findByEmail(email);

        if(user==null){
            log.error("[LoginService loadUserByUsername] 이메일이 존재하지 않습니다. email :: {}", email);
            throw new UserEmailNotFoundException();
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new CustomUserDetail(user, authorities);
    }
}
