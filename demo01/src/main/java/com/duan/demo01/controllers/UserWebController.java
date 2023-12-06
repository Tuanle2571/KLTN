package com.duan.demo01.controllers;

import com.duan.demo01.models.UserEntity;
import com.duan.demo01.servies.UserService;
import com.duan.demo01.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserWebController {
    UserService userService;

    @Autowired
    public UserWebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String home(Model model) {
        List<UserEntity> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user-page";
    }


    @GetMapping("/detail/{id}")
    public String userDetail(Model model, @PathVariable("id") String id) {
        UserEntity user = userService.findUser(id);
        model.addAttribute("user", user);
        return "user-detail";
    }

    @PostMapping("/update")
    public String updateUserPost(@ModelAttribute("user") UserEntity user, @PathVariable("file") MultipartFile file) {

        if (!(file.isEmpty() | file == null)) {
            String image = userService.saveUserThumbnail(user.getId(), file);
            user.setImage(image);
        }

        userService.update(user);

        return "redirect:/user/detail/" + user.getId();
    }

    @GetMapping("/image/{id}")
    public byte[] getQRImage(@PathVariable String id) {
        try {
            byte[] image = userService.getUserThumbnail(id);
            return image;
        } catch (Exception e) {
            return null;
        }
    }
}
