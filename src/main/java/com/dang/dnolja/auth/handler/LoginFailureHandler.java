package com.dang.dnolja.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private DefaultRedirectStrategy defaultRedirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {


        //인증 예외가 disabled일 때만 해당 url 타고 이동
        if(exception instanceof DisabledException){
            defaultRedirectStrategy.sendRedirect(request, response, "auth/disabled");
            return;
        }

        //locked 예외
        if(exception.getCause() instanceof LockedException){
            defaultRedirectStrategy.sendRedirect(request, response, "auth/locked");
            return;
        }

        // 그 외의 모든 예외는 Logine error 취급
        defaultRedirectStrategy.sendRedirect(request, response, "auth/error");
        log.info("로그인에 실패했습니다. 메시지 : {}", exception.getMessage());
    }
}
