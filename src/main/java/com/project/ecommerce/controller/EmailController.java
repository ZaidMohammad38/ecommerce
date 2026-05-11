package com.project.ecommerce.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.service.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }

    @PostMapping
    public String sendEmail(

            @RequestParam String to,

            @RequestParam String subject,

            @RequestParam String text
    ) {

        service.sendEmail(
                to,
                subject,
                text
        );

        return "Email sent successfully";
    }
}
