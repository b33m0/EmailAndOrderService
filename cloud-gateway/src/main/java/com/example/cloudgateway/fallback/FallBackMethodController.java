package com.example.cloudgateway.fallback;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @RequestMapping("/orderServiceFallBack")
    public String orderServiceFallBackMethod() {
        return "Order Service is taking longer than Expected." +
                " Please try again later";
    }

    @RequestMapping("/emailServiceFallBack")
    public String emailServiceFallBackMethod() {
        return "Email Service is taking longer than Expected." +
                " Please try again later";
    }
}
