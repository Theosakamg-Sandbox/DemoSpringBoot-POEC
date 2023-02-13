package com.epsi.spring.mg.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    @GetMapping("/hello/template")
    public String index(Model model) {
        model.addAttribute("username", "Mickael");
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
