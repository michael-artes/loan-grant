package com.loan.grant.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PersonDTO {
    private Long id;
    private String name;
    private String identifier;
    private LocalDate birthDate;
    private String identifierType;
    private BigDecimal minimumInstallmentValue;
    private BigDecimal maximumLoanValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }

    public BigDecimal getMinimumInstallmentValue() {
        return minimumInstallmentValue;
    }

    public void setMinimumInstallmentValue(BigDecimal minimumInstallmentValue) {
        this.minimumInstallmentValue = minimumInstallmentValue;
    }

    public BigDecimal getMaximumLoanValue() {
        return maximumLoanValue;
    }

    public void setMaximumLoanValue(BigDecimal maximumLoanValue) {
        this.maximumLoanValue = maximumLoanValue;
    }
}
