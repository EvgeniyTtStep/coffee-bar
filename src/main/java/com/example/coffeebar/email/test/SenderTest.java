package com.example.coffeebar.email.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SenderTest {


    private final JavaMailSender mailSender;

    @Autowired
    public SenderTest(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendMail(String mail) {
        String msg = "Привіт, це тестовий лист для перевірки";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(mail);
        email.setFrom("46program@ukr.net");
        email.setSubject("Java31 test mail");
        email.setText(msg);
        mailSender.send(email);
    }

}
