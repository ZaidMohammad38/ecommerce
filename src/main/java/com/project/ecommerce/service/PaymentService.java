package com.project.ecommerce.service;

import org.json.JSONObject;

public interface PaymentService {

    JSONObject createOrder(
            Integer amount
    ) throws Exception;
}
