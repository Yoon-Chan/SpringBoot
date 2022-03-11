package com.chan.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@SpringBootApplication으로 인해 스프링부트의 자동설정, 스프링 Bean읽기와 생성을 모두 자동으로 설정.
//이 클래스는 항상 프로젝트의 최상단에 위치해야만 한다.
//@EnableJpaAuditing  //JPA Auditing 활성화(저장된시간, 수정시간)등을 사용.
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        //Application 클래스는 앞으로 만들 프로젝트의 메인 클래스가 된다.
        //SpringApplication.run을 통해 내장 WAS를 실행한다.
        SpringApplication.run(Application.class, args);
    }
}
