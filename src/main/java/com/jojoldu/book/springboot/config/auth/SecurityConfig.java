package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()//해당 옵션 disable처리 -h2-console 화면을 사용하기 위해
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()//URL별 권한 관리를 설정하는 옵션의 시작점
                    .antMatchers("/", "*/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()//anyMatchers : 권한 관리 대상 지정
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated() //anyRequest : 설정된 값들 이외 나머지 url , authenticated : 인증된 사용자만 허용
                .and()
                    .logout()// logout : 로그아웃 기능에 대한 여러 설정 진입점
                        .logoutSuccessUrl("/")//로그아웃 성공시 해당 url로 이동
                .and()
                    .oauth2Login()//OAuth2 로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint()//로그인 성공 이후 사용자 정보를 가져올 때의 설정
                            .userService(customOAuth2UserService);//소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
    }
}
