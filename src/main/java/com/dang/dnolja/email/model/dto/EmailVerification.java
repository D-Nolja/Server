package com.dang.dnolja.email.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class EmailVerification {

    private String verificationId;
    private String email;

    public EmailVerification(String email, String verificationId) {
        this.email= email;
        this.verificationId = verificationId;
    }
}
