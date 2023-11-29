package com.bank.management.service;

import com.bank.management.entity.Event;
import com.bank.management.feign.OrderFeign;
import com.bank.management.feign.PaymentFeign;
import com.bank.management.feign.ShipmentFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SagaOrchestratorService {

    @Autowired
    OrderFeign orderFeign;

    @Autowired
    PaymentFeign paymentFeign;

    @Autowired
    ShipmentFeign shipmentFeign;

    public void handlePayment(Event event) {
        paymentFeign.makePayment(event.getOrderId());
    }


    public void handleShipment(Event event) {
        if (event.getEventStatus().equalsIgnoreCase("FAILURE")) {
            updateOrder(event);
        } else {
            shipmentFeign.startShipping(event.getOrderId());
        }
    }

    public void handleLastProcess(Event event) {
        if (event.getEventStatus().equalsIgnoreCase("FAILURE")) {
            reversePayment(event);
            updateOrder(event);
        } else {
            updateOrder(event);
        }
    }


    public void reversePayment(Event event) {
        paymentFeign.cancelPayment(event.getOrderId());
    }


    public void updateOrder(Event event) {
        String orderStatus = event.getEventStatus().equalsIgnoreCase("SUCCESS") ? "COMPLETED" : "CANCELLED";
        orderFeign.updateOrder(event.getOrderId(), orderStatus);
    }
}