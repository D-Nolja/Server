package com.dang.dnolja.user.model.mapper;

import com.dang.dnolja.user.model.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {

    void join(UserDto user);
    UserDto findByEmail(String email);

    UserDto findByRefreshToken(String refreshToken);

    void modify(Map<String, Object> map);

    void updateVerified(long id);

    void updateRefreshToken(Map<String, Object> param);
}
