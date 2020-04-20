package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .headers().frameOptions().disable() // h2-console 사용을 위함
                .and()
                .authorizeRequests() // URL 별 권한 관리 옵션 시작
                .antMatchers("/", "/css/**", "/image/**",
                        "/js/**", "/h2-console/**", "/profile").permitAll() // 권한 관리 대상 지정 및 전체 열람 권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 유저 권한만 가능
                .anyRequest().authenticated() // 설정 이외의 URL은 인증된 사용자에게만 허용
                .and()
                .logout() // 로그아웃 설정 관리 옵션 시작
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login() // OAuth 2 로그인 기능에 대한 설정 관리 옵션 시작
                .userInfoEndpoint() // 로그인 성공 후 사용자 정보 가져올 때 설정
                .userService(customOAuth2UserService); // 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체 등록.
    }
}
