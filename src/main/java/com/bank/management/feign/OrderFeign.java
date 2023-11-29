package com.bank.management.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "order-service", url = "${order.service.url}")
public interface OrderFeign {
    @PostMapping(value = "/cancelOrder/{orderId}/{status}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    String cancelOrder(@RequestBody Long orderId);

    @PostMapping(value = "/updateOrder/{orderId}/{status}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    String updateOrder(@PathVariable Long orderId,
                       @PathVariable String status);
}

