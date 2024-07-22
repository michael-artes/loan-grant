package com.loan.grant.controller;

import com.loan.grant.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{loanId}")
    public ResponseEntity<Void> processPayment(@PathVariable Long loanId) {
        paymentService.processPayment(loanId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

