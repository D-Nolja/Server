package com.dang.dnolja.user.controller;

import com.dang.dnolja.event.UserRegistrationEvent;
import com.dang.dnolja.response.CommonResponse;
import com.dang.dnolja.response.ResultCode;
import com.dang.dnolja.user.model.dto.JoinReqDto;
import com.dang.dnolja.user.model.dto.UserDto;
import com.dang.dnolja.user.model.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.transform.Result;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {





}
