package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * SpringBootApplication 어노테이션이 있는 위치 부터 설정을 읽음
 * 항상 프로젝트 최상단에 있어야함
 * 스프링 부트의 자동설정, 스프링 Bean읽기와 생성을 모두 자동으로 설정
 */
@EnableJpaAuditing  //JPA Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
