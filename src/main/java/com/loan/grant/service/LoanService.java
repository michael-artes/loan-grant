package com.loan.grant.service;

import com.loan.grant.dto.LoanDTO;
import com.loan.grant.exceptions.InvalidIdentifierException;
import com.loan.grant.exceptions.PersonNotFoundException;
import com.loan.grant.message.PaymentMessage;
import com.loan.grant.model.Loan;
import com.loan.grant.model.Person;
import com.loan.grant.repository.LoanRepository;
import com.loan.grant.repository.PersonRepository;
import com.loan.grant.validation.IdentifierValidator;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private IdentifierValidator identifierValidator;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String QUEUE_NAME = "payment-queue";

    public LoanDTO createLoan(Long personId, BigDecimal loanAmount, int numberOfInstallments) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found"));

        if (!identifierValidator.isValid(person.getIdentifier(), person.getIdentifierType())) {
            throw new InvalidIdentifierException("Invalid identifier");
        }

        validateLoan(person, loanAmount, numberOfInstallments);

        Loan loan = new Loan();
        loan.setPerson(person);
        loan.setLoanAmount(loanAmount);
        loan.setNumberOfInstallments(numberOfInstallments);
        loan.setPaymentStatus("PENDING");
        loan.setCreationDate(LocalDateTime.now());

        loan = loanRepository.save(loan);

        rabbitTemplate.convertAndSend(QUEUE_NAME, new PaymentMessage(loan.getId()));

        return convertToDTO(loan);
    }

    private void validateLoan(Person person, BigDecimal loanAmount, int numberOfInstallments) {
        //TODO aqui poderia ter uma regra para pegar a soma de todos os empréstimos pagos
        if (loanAmount.compareTo(person.getMaximumLoanValue()) > 0) {
            throw new IllegalArgumentException("Loan amount exceeds the maximum limit");
        }
        if (loanAmount.divide(new BigDecimal(numberOfInstallments), RoundingMode.CEILING)
                .compareTo(person.getMinimumInstallmentValue()) < 0) {
            throw new IllegalArgumentException("Monthly payment is less than the minimum allowed");
        }
        if (numberOfInstallments > 24) {
            throw new IllegalArgumentException("Number of installments exceeds the limit of 24");
        }
    }

    private LoanDTO convertToDTO(Loan loan) {
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setId(loan.getId());
        loanDTO.setPersonId(loan.getPerson().getId());
        loanDTO.setLoanAmount(loan.getLoanAmount());
        loanDTO.setNumberOfInstallments(loan.getNumberOfInstallments());
        loanDTO.setPaymentStatus(loan.getPaymentStatus());
        loanDTO.setCreationDate(loan.getCreationDate());
        return loanDTO;
    }
}
