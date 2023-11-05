package com.dang.dnolja.event;

import com.dang.dnolja.user.model.dto.UserDto;
import org.springframework.context.ApplicationEvent;

public class UserRegistrationEvent extends ApplicationEvent {


    private static final long serialVersionUID = -2685172945219633123L;


    private UserDto userDto;

    public UserRegistrationEvent(UserDto applicationUser){
        super(applicationUser);
        this.userDto = applicationUser;
    }

    public UserDto getUser(){
        return userDto;
    }


}