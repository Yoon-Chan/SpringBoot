package com.chan.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//컨트롤러를 JSON을 반환하는 컨트롤러로 만들어준다.
@RestController
public class HelloController {

    //@GetMapping
    //Http Method인 Get의 요청을 받을 수 있는 API를 만들어줍니다.
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

}
