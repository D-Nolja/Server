package com.dang.dnolja.user.model.mapper;

import com.dang.dnolja.user.model.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void join(UserDto user);
}
