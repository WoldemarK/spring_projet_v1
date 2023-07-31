package com.example.springprojetv1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping
    public String testingMethod(String s){
        return "The testing method " + s;
    }
}
