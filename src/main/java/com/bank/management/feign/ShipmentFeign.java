package com.bank.management.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "shipment-service", url = "${shipment.service.url}")
public interface ShipmentFeign {
    @PostMapping(value = "/startShipping", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    String startShipping(@RequestBody Long orderId);
}

