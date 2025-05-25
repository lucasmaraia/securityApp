package com.br.securityApp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class HelloController {

    @GetMapping
    public String hello(HttpServletRequest request){
        return "Welcome to my project " + request.getSession().getId();
    }
}
