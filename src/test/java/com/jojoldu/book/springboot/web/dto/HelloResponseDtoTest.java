package com.jojoldu.book.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void lombokTest(){
        //given
        String name = "김지영";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);  //assertThat : 테스트 검증 라이브러리 검증 메소드
                                                    // Junit < assertj 라이브러리가 편리
        assertThat(dto.getAmount()).isEqualTo(amount);  //isEqualTo : assertThat의 값과 비교해서 같은 경우 성공 
    }
}
