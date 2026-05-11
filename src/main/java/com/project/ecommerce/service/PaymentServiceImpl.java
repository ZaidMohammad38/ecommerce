package com.project.ecommerce.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final RazorpayClient razorpayClient;

    public PaymentServiceImpl(
            RazorpayClient razorpayClient) {

        this.razorpayClient = razorpayClient;
    }

    @Override
    public JSONObject createOrder(
            Integer amount) throws Exception {

        JSONObject options =
                new JSONObject();

        options.put(
                "amount",
                amount * 100
        );

        options.put(
                "currency",
                "INR"
        );

        options.put(
                "receipt",
                "txn_123456"
        );

        Order order =
                razorpayClient.orders.create(
                        options
                );

        return order.toJson();
    }
}
