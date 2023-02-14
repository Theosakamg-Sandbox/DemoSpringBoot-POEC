package com.epsi.spring.mg.demo.controllers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epsi.spring.mg.demo.models.User;

@Controller
@RequestMapping("/users")		// Prefix Path by /users/....
public class UserController {

    public static final String USER_MODEL = "current_user";

    private User currentUser;

    public UserController() {
        this.currentUser = new User()
                .setFirstname("Mickael")
                .setLastname("Gaillard")
                .setDob(LocalDateTime.parse("2000-08-08T00:00:00"));
    }

    @GetMapping(CommonConstant.ROUTE_SHOW)   // => /users/120/show
    //@RequestMapping(method = RequestMethod.GET, path = CommonConstant.ROUTE_SHOW)	// Base of @GetMapping(...)
    public String viewProfil(Model model, @PathVariable("id") long id) {
        // list<User> ??? id ?
        model.addAttribute(USER_MODEL, this.currentUser);
        return "users/profil";
    }

    @GetMapping(CommonConstant.ROUTE_EDIT)
    public String editUser(Model model) {
        model.addAttribute(USER_MODEL, this.currentUser);
        return "users/form";
    }

    @PostMapping(CommonConstant.ROUTE_SAVE)
    public String saveUser(Model model, @ModelAttribute User userSubmit) {
        this.currentUser.setFirstname(userSubmit.getFirstname());
        this.currentUser.setLastname(userSubmit.getLastname());

        return "redirect:/users/show";
    }

}
