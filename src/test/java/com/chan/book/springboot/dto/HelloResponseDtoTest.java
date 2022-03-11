package com.chan.book.springboot.dto;

import com.chan.book.springboot.web.dto.HelloResponseDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void lombok_test(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        //assertThat: assertj라는 테스트 검증 라이브러리의 검증 메소드.
        //검증하고 싶은 대상을 메소드 인자로 받습니다.
        //메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용할 수 있습니다.
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
