package com.loan.grant.controller;

import com.loan.grant.dto.LoanDTO;
import com.loan.grant.dto.LoanRequestDTO;
import com.loan.grant.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@RequestBody LoanRequestDTO loanRequestDTO) {
        LoanDTO loanDTO = loanService.createLoan(
                loanRequestDTO.getPersonId(),
                loanRequestDTO.getLoanAmount(),
                loanRequestDTO.getNumberOfInstallments()
        );
        return new ResponseEntity<>(loanDTO, HttpStatus.CREATED);
    }
}

