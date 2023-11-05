package com.dang.dnolja.listener;


import com.dang.dnolja.email.model.service.EmailVerificationService;
import com.dang.dnolja.event.UserRegistrationEvent;
import com.dang.dnolja.user.model.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@AllArgsConstructor
public class EmailVerificationListener implements ApplicationListener<UserRegistrationEvent> {

    private final JavaMailSender mailSender;
    private final EmailVerificationService emailVerificationService;



    //UserRegistrationevent 발생시 해당 메서드가 호출된다.
    @Override
    public void onApplicationEvent(UserRegistrationEvent event) {
        UserDto user = event.getUser();
        String email = user.getEmail();
        //사용자 등록시 생성된 verificationId를 조회한다.
        String verificationId = emailVerificationService.generateVerification(email);


        //메시지 세팅
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("회원 가입 인증 메일입니다.");
        message.setText(getText(user, verificationId));

        //메시지 보내기
        message.setTo(email);
        mailSender.send(message);
    }


    private String getText(UserDto user, String verificationId){



        StringBuffer buffer = new StringBuffer();
        buffer.append(user.getUsername()).append("님 댕놀자 가입을 환영합니다. ").append(System.lineSeparator()).append(System.lineSeparator());

        buffer.append("계정 활성화를 위해 다음 링크로 접속해주세요 : http://localhost:8080/verify/email?id=").append(verificationId);
        buffer.append(System.lineSeparator()).append(System.lineSeparator());
        buffer.append("올림,").append(System.lineSeparator()).append("댕놀자 Team");
        return buffer.toString();


    }
}
