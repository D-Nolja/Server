package com.dang.dnolja.auth.filter;

import com.dang.dnolja.auth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException, IOException {
        log.error("일단 필터는 거쳐요....");
        HttpServletRequest req = (HttpServletRequest) request;
        if(req.getHeader("Authorization")!=null) {

            String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
            log.error(((HttpServletRequest) request).getHeader("Authorization"));
            ;
            log.error(token);

            if (token != null && jwtTokenProvider.validateTokenExceptExpiration(token)) {
                log.error("유효성검사 완료");
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                log.error(" auth ::: {}",auth.getDetails().toString());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }
}
