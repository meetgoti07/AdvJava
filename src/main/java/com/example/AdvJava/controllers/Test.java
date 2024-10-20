package com.example.AdvJava.controllers;

//Hello World Output
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @RequestMapping("/")
    public String index() {
        return "Hello World!";
    }
}
