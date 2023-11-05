package com.dang.dnolja.email.model.mapper;

import com.dang.dnolja.email.model.dto.EmailVerification;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface EmailVerificationMapper {

    Optional<EmailVerification> findById(String verificationId);
    Optional<EmailVerification> findByEmail(String email);
    void save(EmailVerification emailVerification);

}
