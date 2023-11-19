package com.dang.dnolja.user.model.service;

import com.dang.dnolja.auth.dto.request.JoinReqDto;
import com.dang.dnolja.user.model.dto.UserDto;


public interface UserService {

    public void modify(Long id, String username, String profile);

    public UserDto join(JoinReqDto req);

    public UserDto findByEmail(String email);

    public void updateVerified(long userId);
}
