package com.loan.grant.service;

import com.loan.grant.dto.LoanDTO;
import com.loan.grant.model.Loan;
import com.loan.grant.model.Person;
import com.loan.grant.repository.LoanRepository;
import com.loan.grant.repository.PersonRepository;
import com.loan.grant.validation.IdentifierValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanServiceTest {

    @InjectMocks
    private LoanService loanService;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private IdentifierValidator identifierValidator;

    @Mock
    private RabbitTemplate rabbitTemplate;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPerformLoan() {
        Person person = new Person();
        person.setId(1L);
        person.setName("User test");
        person.setIdentifier("12345678901");
        person.setIdentifierType(person.getIdentifier());

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(identifierValidator.isValid(person.getIdentifier(), person.getIdentifierType())).thenReturn(true);

        Loan loan = new Loan();
        loan.setId(1L);
        loan.setPerson(person);
        loan.setLoanAmount(new BigDecimal("5000"));
        loan.setNumberOfInstallments(10);
        loan.setPaymentStatus("PENDING");
        loan.setCreationDate(LocalDateTime.now());

        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        LoanDTO loanDTO = loanService.createLoan(1L, new BigDecimal("5000"), 10);

        assertNotNull(loanDTO);
        assertEquals(loanDTO.getPersonId(), person.getId());
        assertEquals(loanDTO.getLoanAmount(), new BigDecimal("5000"));
        assertEquals(loanDTO.getNumberOfInstallments(), 10);
    }
}