package com.project.ecommerce.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin("*")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(
            PaymentService service) {

        this.service = service;
    }

    @PostMapping("/create-order")
    public String createOrder(

            @RequestParam Integer amount
    ) throws Exception {

        JSONObject order =
                service.createOrder(amount);

        return order.toString();
    }
}
