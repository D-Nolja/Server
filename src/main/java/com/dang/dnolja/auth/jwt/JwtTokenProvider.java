package com.dang.dnolja.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${spring.jwt.secretKey}")
    private String secretKey;

    public static String TOKEN_PREFIX = "Bearer ";
    public static String HEADER_STRING = "Authorization";

    private long expireTime = 1000L * 60 * 30; // 30분

    public String getEmail(String token) {
        return JWT.require(Algorithm.HMAC512(secretKey)).build().verify(token)
                .getClaim("email").asString();
    }


    public String createToken(String email) {
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis()+expireTime))
                .withClaim("email", email)
                .sign(Algorithm.HMAC512(secretKey));
    }
}
