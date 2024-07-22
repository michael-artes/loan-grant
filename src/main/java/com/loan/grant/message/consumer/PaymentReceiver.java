package com.loan.grant.message.consumer;

import com.loan.grant.message.PaymentMessage;
import com.loan.grant.service.PaymentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentReceiver {

    @Autowired
    private PaymentService paymentService;

    @RabbitListener(queues = "payment-queue")
    public void receiveMessage(PaymentMessage message) {
        paymentService.processPayment(message.getLoanId());
    }
}

