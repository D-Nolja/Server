package com.dang.dnolja.email.model.mapper;

import com.dang.dnolja.email.model.dto.EmailVerification;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface EmailVerificationMapper {

    EmailVerification findById(String verificationId);
    EmailVerification findByEmail(String email);
    void save(EmailVerification emailVerification);

}
