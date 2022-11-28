package com.Ezenweb.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configurable
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override // 재정의 [ 상속받은 클래스로부터 매소드 재구현 ]
    protected void configure(HttpSecurity http) throws Exception {
       // super.configure(http); // 시큐리티 기본 http 보안
    }
}

/*
    시큐리티 사용방법
        // 1. 그레이들 의존성 추가
            implementation 'org.springframework.boot:spring-boot-starter-security' // 스프링 시큐리티 [ 인증 / 인가 ]
        // 2. 시큐리티 커스텀 [ 기본 설정값 변경 ]
            @Configuration : 컴포넌트 시리즈 [ @Controller , @Service , @Entity , @Configuration 등 ]
            extends WEbSecurityConfigurerAdapter 클래스 상속받아서 커스텀 클래스 선언
                1. configure(HttpSecurity http) : http 보안 메소드
                2.


    시큐리티 기본값
            1. 해당 프로젝트의 모든 URL 잠긴다.
                -> 커스텀 : http 권한 없애기

            2. 기본 LOGIN 페이지 제공 -> 커스텀 login 페이지 변경가능
            3. 기본 LOGIN Controller 제공
            4. 기본 LOGIN Service 제공
            -----------> 커스텀 작업 -> SecurityConfiguration : 시큐리티 설정 클래스
                // WebSecurityConfigurerAdapter : 웹 시큐리티 설정 클래스
                    // 설정 종류
                        // 1. URL 권한
*/
