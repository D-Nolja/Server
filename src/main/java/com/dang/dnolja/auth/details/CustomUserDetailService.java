package com.dang.dnolja.auth.details;

import com.dang.dnolja.common.Exception.UserEmailNotFoundException;
import com.dang.dnolja.user.model.dto.UserDto;
import com.dang.dnolja.user.model.mapper.UserMapper;
import com.dang.dnolja.user.model.service.UserService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto user = userMapper.findByEmail(email);
        log.error(user.toString());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if(user == null){
            throw new UserEmailNotFoundException();
        }

        return new CustomUserDetail(user, authorities);
    }
}
