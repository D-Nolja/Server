package com.dang.dnolja.auth.config;

import com.dang.dnolja.auth.details.CustomUserDetailService;
import com.dang.dnolja.auth.filter.JwtAuthenticationFilter;
import com.dang.dnolja.auth.filter.JwtAuthorizationFilter;
import com.dang.dnolja.auth.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final CorsConfig corsConfig;


    private final JwtTokenProvider jwtTokenProvider;

    private final CustomUserDetailService userDetailService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilter(corsConfig.corsFilter())
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(jwtTokenProvider, authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtTokenProvider, userDetailService))
                .authorizeRequests()
                .antMatchers("/auth/*", "/verify/*").permitAll()
                .antMatchers("/authCheck", "/recommendation/*").authenticated();

    }
}
