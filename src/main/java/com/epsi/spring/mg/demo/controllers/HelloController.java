package com.epsi.spring.mg.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String index() {
        return "<h1>Hello W !</h1>";
    }


}
