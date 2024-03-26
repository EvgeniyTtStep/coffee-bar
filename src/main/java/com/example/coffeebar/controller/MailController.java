package com.example.coffeebar.controller;

import com.example.coffeebar.email.test.MyMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MailController {


    private final MyMailSender senderTest;

    @Autowired
    public MailController(MyMailSender senderTest) {
        this.senderTest = senderTest;
    }

    @GetMapping("/send/mail")
    public String sendmail(@RequestParam(name = "email") String email) {
        //senderTest.confirmRegistration(email);
        return "redirect:/";
    }


}
