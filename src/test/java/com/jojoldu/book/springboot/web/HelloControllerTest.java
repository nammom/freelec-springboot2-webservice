package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * test 진행 전 설정 변경
 * settings > gradle > Run tests using : IntelliJ IDEA
 */
@RunWith(SpringRunner.class)        //테스트 진행시 JUnit 실행자 외에 다른 실행자를 실행 - SpringRunner 스프링 실행자
@WebMvcTest                         //Web에 집중할 수 있는 테스트 - Controller, ControllerAdvice 등 사용 가능(Service, Component, Repository X)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;    //web API를 테스트할 때 사용 - HTTP GET, POST 테스트 가능

    @Test
    public void returnHello() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))   // /hello 주소로 HTTP GET요청
                .andExpect(status().isOk())     //perform의 결과 status 검증
                .andExpect(content().string(hello));    //perform의 결과 responseBody 내용 검증 - 응답결과가 hello가 맞는지

    }

    @Test
    public void returnHelloDto() throws Exception{
        String name = "김지영";
        int amount = 1000;

        mvc.perform(get("/hello/dto")                             // /hello/dto 주소로 HTTP GET요청
                        .param("name", name)                          //parameter 추가 (String만 가능)
                        .param("amount", String.valueOf(amount)))   
                .andExpect(status().isOk())                                 //perform의 결과 status 검증
                .andExpect(jsonPath("$.name", is(name)))           //json응담값을 필드별로 검증할 수 있음 - $.필드명
                .andExpect(jsonPath("$.amount", is(amount)));;

    }

}
