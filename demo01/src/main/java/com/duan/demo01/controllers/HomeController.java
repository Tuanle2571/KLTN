package com.duan.demo01.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

    @RequestMapping("")
    public String index(){
        return "layout";
    }
    @RequestMapping("/page1")
    public String page1() {
        return "test";
    }

    @RequestMapping("/page2")
    public String page2() {
        return "page2";
    }
}

