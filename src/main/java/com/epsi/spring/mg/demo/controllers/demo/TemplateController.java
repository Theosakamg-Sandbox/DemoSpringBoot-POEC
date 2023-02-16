package com.epsi.spring.mg.demo.controllers.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.epsi.spring.mg.demo.models.Book;

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

    @GetMapping("/hello/form")
    public String formView(Model model) {
        Book book = new Book()
                .setName("MySuperBook")
                .setIsbn("666");
        model.addAttribute("new_book", book);
        return "demo/form";
    }

    @PostMapping("/form-submit")
    public String formSubmit(@ModelAttribute Book bookCompleted, Model model) {
        //System.out.println(bookCompleted.toString());
        model.addAttribute("new_book", bookCompleted);
        return "demo/form";
    }
}
