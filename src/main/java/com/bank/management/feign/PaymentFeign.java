package com.bank.management.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "payment-service", url = "${payment.service.url}")
public interface PaymentFeign {
    @PostMapping(value = "/makePayment", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    String makePayment(@RequestBody Long orderId);

    @PostMapping(value = "/cancelPayment", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    String cancelPayment(@RequestBody Long orderId);
}

