package com.jojoldu.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor    //final이 붙은 필드만 생성자에 포함
public class HelloResponseDto {

    private final String name;
    private final int amount;
    
}
