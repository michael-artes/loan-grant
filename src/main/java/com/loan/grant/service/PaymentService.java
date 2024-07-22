package com.loan.grant.service;

import com.loan.grant.model.Loan;
import com.loan.grant.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PaymentService {

    @Autowired
    private LoanRepository loanRepository;

    public void processPayment(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Loan not found"));

        if ("PAID".equals(loan.getPaymentStatus())) {
            throw new IllegalArgumentException("Loan already paid");
        }

        loan.setPaymentStatus("PAID");
        loanRepository.save(loan);
    }
}

