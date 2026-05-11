package com.project.ecommerce.service;

public interface EmailService {

    void sendEmail(
            String to,
            String subject,
            String text
    );
}