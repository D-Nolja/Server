package com.dang.dnolja.auth.details;

import com.dang.dnolja.user.model.dto.UserDto;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@ToString
public class CustomUserDetail implements UserDetails {

    private UserDto userDto;
    private List<GrantedAuthority> authorities;

    public CustomUserDetail(UserDto userDto, List<GrantedAuthority> authorities) {
        this.userDto = userDto;
        this.authorities = authorities;
    }

    public UserDto getUser(){
        return userDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDto.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(this.userDto.getVerified()==0){
            return false;
        }else{
            return true;
        }
    }
}
