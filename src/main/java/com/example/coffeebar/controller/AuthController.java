package com.example.coffeebar.controller;


import com.example.coffeebar.entity.User;
import com.example.coffeebar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.resource.HttpResource;

import javax.management.relation.RoleNotFoundException;
import java.net.http.HttpResponse;
import java.security.Principal;

@Controller
public class AuthController {

    private final UserService userService;


    @GetMapping("/")
    public String index(Principal principal, Model model) {

        if (principal != null) {
            String principalName = principal.getName();
            User userByUsername = userService.findUserByUsername(principalName);
            if (userByUsername != null) {
                System.out.println("userByUsername = " + userByUsername.getUsername());
                model.addAttribute("user", userByUsername);
            }
        }
        return "index";
    }


    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/registration")
    public String reg(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }


    @PostMapping("/registration")
    public String reg(@ModelAttribute User user) throws RoleNotFoundException {
        userService.save(user);
        return "index";
    }


}
