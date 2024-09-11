package com.kholanhcaonguyen.PMQL.controller;

import com.kholanhcaonguyen.PMQL.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail() {
        emailService.sendSimpleEmail("nhanth95@gmail.com", "Test Subject", "This is a test email.");
        return "Email sent successfully";
    }
}
