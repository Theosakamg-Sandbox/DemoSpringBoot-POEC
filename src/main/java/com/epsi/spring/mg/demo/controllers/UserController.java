package com.epsi.spring.mg.demo.controllers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epsi.spring.mg.demo.models.User;

@Controller
@RequestMapping("/users")
public class UserController {

    public static final String USER_MODEL = "current_user";

    @GetMapping("/mick")
    public String viewProfil(Model model) {
        User user = new User()
            .setFirstname("Mickael")
            .setLastname("Gaillard")
            .setDob(LocalDateTime.parse("2000-08-08T00:00:00"));


        model.addAttribute(USER_MODEL, user);
        return "users/profil";
    }
}
