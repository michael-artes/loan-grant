package com.loan.grant.message;

import java.io.Serializable;

public class PaymentMessage implements Serializable {


    private Long loanId;

    public PaymentMessage() {}

    public PaymentMessage(Long loanId) {
        this.loanId = loanId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }
}
