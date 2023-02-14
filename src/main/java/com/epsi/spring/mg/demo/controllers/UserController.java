package com.epsi.spring.mg.demo.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

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
    public static final String USERS_MODEL = "users";

    //private User currentUser;
    private ArrayList<User> users = new ArrayList<>();

    public UserController() {
        users.add(new User()
                .setFirstname("Mickael")
                .setLastname("Gaillard")
                .setDob(LocalDateTime.parse("2000-08-08T00:00:00"))
                .setId(1));
        users.add(new User().setFirstname("David").setLastname("Cuomo").setId(2));
        users.add(new User().setFirstname("Florian").setLastname("Sibois").setId(3));
        users.add(new User().setFirstname("Antoine").setLastname("Castel").setId(4));
    }

    @GetMapping(CommonConstant.ROUTE_ALL)
    public String showAll(Model model) {
        model.addAttribute(USERS_MODEL, users);

        return "users/list";
    }


    @GetMapping(CommonConstant.ROUTE_SHOW)   // => /users/120/show
    //@RequestMapping(method = RequestMethod.GET, path = CommonConstant.ROUTE_SHOW)	// Base of @GetMapping(...)
    public String viewProfil(Model model, @PathVariable("id") long id) {
        User userFinded = this.findUserById(id);

        if (userFinded != null) {
            model.addAttribute(USER_MODEL, userFinded);
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
        for (User user : this.users) {
            if (user.getId() == id) {
                userFinded = user;
                break;
            }
        }
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

        model.addAttribute(USER_MODEL, userFinded);
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
