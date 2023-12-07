package com.duan.demo01.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class HomeController {

    @RequestMapping("")
    public String home(){
        return "redirect:/dashboard";
    }

    @RequestMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }
}

