package com.epsi.spring.mg.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epsi.spring.mg.demo.entities.User;
import com.epsi.spring.mg.demo.repositories.UserRepository;

@Controller
@RequestMapping("/users")		// Prefix Path by /users/....
public class UserController {

    public static final String MODEL_ONE = "current_user";
    public static final String MODEL_ALL = "users";

    @Autowired
    private UserRepository repo;

    @GetMapping(CommonConstant.ROUTE_ALL)
    public String showAll(Model model) {
        model.addAttribute(MODEL_ALL, this.repo.findAll());

        return "users/list";
    }


    @GetMapping(CommonConstant.ROUTE_SHOW)   // => /users/120/show
    //@RequestMapping(method = RequestMethod.GET, path = CommonConstant.ROUTE_SHOW)	// Base of @GetMapping(...)
    public String viewProfil(Model model, @PathVariable("id") long id) {
        User userFinded = this.repo.findById(id).orElse(new User());

        if (userFinded != null) {
            model.addAttribute(MODEL_ONE, userFinded);
        }

        return "users/profil";
    }


    /**
     * @param id
     * @param userFinded
     * @return
     */
    private User findUserById(long id) {
        User userFinded = null;
        // Foreach
//        for (User user : this.users) {
//            if (user.getId() == id) {
//                userFinded = user;
//                break;
//            }
//        }
//      // Use For
//      for (int i = 0; i <= users.size() - 1; i++) {
//      	User user = users.get(i);
//      	if (user.getId() == id) {
//              userFinded = user;
//              break;
//          }
//		}
//      // Use Lambda
//      users.stream().findFirst(e => e.getId() == id);

        return userFinded;
    }

    @GetMapping(CommonConstant.ROUTE_EDIT)
    public String editUser(Model model, @PathVariable("id") long id) {
        User userFinded = this.findUserById(id);

        model.addAttribute(MODEL_ONE, userFinded);
        return "users/form";
    }

    @PostMapping(CommonConstant.ROUTE_SAVE)
    public String saveUser(Model model, @ModelAttribute User userSubmit) {
        User userFinded = this.findUserById(userSubmit.getId());

        if (userFinded != null) {
            userFinded.setFirstname(userSubmit.getFirstname());
            userFinded.setLastname(userSubmit.getLastname());
        }

        return "redirect:/users/" + userFinded.getId() + "/show";
    }

}
