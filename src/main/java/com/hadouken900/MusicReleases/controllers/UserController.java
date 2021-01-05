package com.hadouken900.MusicReleases.controllers;

import com.hadouken900.MusicReleases.entities.User;
import com.hadouken900.MusicReleases.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String userRegistrationForm() {
        return "registration";
    }


    @PostMapping("/registration")
    public String registerUser(
            Model model,
            @RequestParam String username,
            @RequestParam String password
    ){

        if (!userService.saveUser(new User(username,password))){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/";
    }
}
