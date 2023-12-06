package com.duan.demo01.controllers;

import com.duan.demo01.models.UserEntity;
import com.duan.demo01.servies.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        UserEntity userEntity = new UserEntity();
        model.addAttribute("userEntity", userEntity);
        return "register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("userEntity") UserEntity userEntity,
                           BindingResult result, Model model) {
        UserEntity existingUserEmailEntity = userService.findByEmail(userEntity.getEmail());
        if(existingUserEmailEntity != null && existingUserEmailEntity.getEmail() != null && !existingUserEmailEntity.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }
        UserEntity existingUserUsername = userService.findByUsername(userEntity.getUsername());
        if(existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
            return "redirect:/register?fail";
        }
        if(result.hasErrors()) {
            model.addAttribute("user", userEntity);
            return "register";
        }
        userService.saveUser(userEntity);
        return "redirect:/device?success";
    }
}
