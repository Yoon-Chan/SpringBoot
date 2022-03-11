package com.chan.book.springboot.config.auth;

import com.chan.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity : Spring Security설정들을 활성화시켜준다.
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                //아래 두줄 : h2-console화면을 사용하기 위해 해당 옵션들을 disable합니다.
                .csrf().disable()
                .headers().frameOptions().disable()
                //authorizeRequest() : URL별 권한 관리를 설정하는 옵션의 시작점.
                //이 함수를 사용해야 antMatchers를 사용할 수 있다.
                .and()
                .authorizeRequests()
                //antMatchers : 권한 관리 대상을 지정하는 옵션.
                //URL,HTTP메소드별로 관리가 가능하다.
                //"/"등 지정된 URL들은 permitAll()옵션을 통해 전체 열람 권한을 주었습니다.
                // "/api/v1/**" 주소를 가진 API는 USER권한을 가진 사람만 가능하도록 함.
                .antMatchers("/","/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                //logout().logoutSuccessUrl("/") : 로그아웃 기능에 대한 여러 설정의 진입점.
                //로그아웃 성공시 "/"주소로 이동.
                .and().logout().logoutSuccessUrl("/")
                //로그인 기능에 대한 여러 설정의 진입점.
                //userInfoEndpoint : 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당.
                //userService() : 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록.
                .and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }

}
