package com.bank.management.messaging;

import com.bank.management.entity.Event;
import com.bank.management.feign.PaymentFeign;
import com.bank.management.feign.ShipmentFeign;
import com.bank.management.service.SagaOrchestratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrchestratorReceiver {
    private static final String TOPIC_ORDER = "orderTopic";
    private static final String TOPIC_PAYMENT = "paymentTopic";
    private static final String TOPIC_SHIPMENT = "shipmentTopic";

    @Autowired
    private SagaOrchestratorService service;

    @KafkaListener(topics = TOPIC_ORDER, groupId = "order-group", containerFactory = "tranRecordListener")
    private void listenOrderServiceResponse(Event event) throws Exception {
        log.info("Received message :" + event + " in " + TOPIC_ORDER);
        System.out.println("Received message :" + event + " in " + TOPIC_ORDER);
        service.handlePayment(event);
    }

    @KafkaListener(topics = TOPIC_PAYMENT, groupId = "order-group", containerFactory = "tranRecordListener")
    private void listenPaymentServiceResponse(Event event) throws Exception {
        log.info("Received message :" + event + " in " + TOPIC_PAYMENT);
        System.out.println("Received message :" + event + " in " + TOPIC_PAYMENT);
        service.handleShipment(event);
    }

    @KafkaListener(topics = TOPIC_SHIPMENT, groupId = "order-group", containerFactory = "tranRecordListener")
    private void listenShippingServiceResponse(Event event) throws Exception {
        log.info("Received message :" + event + " in " + TOPIC_PAYMENT);
        System.out.println("Received message :" + event + " in " + TOPIC_PAYMENT);
        service.handleLastProcess(event);
    }
}
