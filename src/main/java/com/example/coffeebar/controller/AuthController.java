package com.example.coffeebar.controller;


import com.example.coffeebar.entity.User;
import com.example.coffeebar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.management.relation.RoleNotFoundException;

@Controller
public class AuthController {

    private final UserService userService;


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
