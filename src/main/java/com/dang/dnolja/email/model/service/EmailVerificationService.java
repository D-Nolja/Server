package com.dang.dnolja.email.model.service;


import com.dang.dnolja.email.model.dto.EmailVerification;
import com.dang.dnolja.email.model.mapper.EmailVerificationMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationMapper emailVerificationMapper;


    //등록된 사용자 이메일에 해당하는 EmailVerification을 생성하고 저장
    public String generateVerification(String email) {
        if (emailVerificationMapper.findByEmail(email) == null) {

            UUID uuid = UUID.randomUUID();
            EmailVerification verification = new EmailVerification(email, uuid.toString());
            emailVerificationMapper.save(verification);
            return verification.getVerificationId();
        }
        return getVerificationIdByEmail(email);
    }

    //이메일에 해당하는 EmailVerification의 식별자인 verificationId를 만든다.
    public String getVerificationIdByEmail(String email) {
        EmailVerification verification = emailVerificationMapper.findByEmail(email);
        if(verification !=null) {
            return verification.getVerificationId();
        }
        return null;
    }

    //식별 id에 해당하는 이메일을 반환한다.
    public String getEmailFromVerificationId(String verificationId){
        EmailVerification verification = emailVerificationMapper.findById(verificationId);

        log.debug("[EmailVerificationService getEmailFromVerificationId] verification :: {}",verification);
        if(verification != null){
            return verification.getEmail();
        }

        return null;


    }



}