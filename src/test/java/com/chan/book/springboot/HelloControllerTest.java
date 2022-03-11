package com.chan.book.springboot;


import com.chan.book.springboot.config.auth.SecurityConfig;
import com.chan.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@RunWith(SpringRuneer.class
//테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킵니다.
//여기서는 SpringRunner라는 스프링 실행자를 사용합니다.
//즉 스프링 부트 테스트와 JUnit t사이에 연결자 역할.

//@WebMvcTest
//여러 스트링 테스트 어노테이션 중, Web에 집중할 수있는 어노테이션
//선언할 경우 @Controller, @ControllerAdvice등을 사용할 수 있다.
//단 @Service, @Component, @Repository등은 사용할 수 없다.
// 여기서는 컨트롤러만 사용하기 때문에 선언
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class,
excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
public class HelloControllerTest {

    //@Autowired : 스프링이 관리하는 빈(Bean)을 주입 받는다.
    @Autowired
    private MockMvc mvc; //웹 API를 테스트할 때 사용. 스프링 MVC테스트의 시작점.
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API테스트를 할 수 있다.


    @WithMockUser(roles = "USER")
    @Test
    public void hello_return() throws Exception{
        String hello = "hello";
        //MockMvc를 통해 /hello 주소로 HTTP GET을 요청
        mvc.perform(get("/hello"))
                //perform의 결과를 검증. 여기서는 OK =200인지 아닌지 검증
                .andExpect(status().isOk())
                //응답 본문의 내용을 검증. Controller에서 hello를 리턴하기 때문에 이값이 맞는지 검증.
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name= "hello";
        int amount = 1000;
                                                //param은 API테스트할 때 사용될 요청 파라미터를 설정. 단 값은 String만 허용된다. 그래서 amount도 String으로 형변환.
        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())

                //jasonPath : JSON응답값을 필드별로 검증할 수 있는 메소드.
                //$를 기준으로 필드명을 명시한다. 여기서는 name과 amount를 검증하니 아래와 같이 작성.
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
