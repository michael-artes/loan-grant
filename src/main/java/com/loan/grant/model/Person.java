package com.loan.grant.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
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

    public void setIdentifierType(String identifier) {
        int length = identifier.length();
        switch (length) {
            case 11:
                this.identifierType = "PF";
                this.minimumInstallmentValue = new BigDecimal("300");
                this.maximumLoanValue = new BigDecimal("10000");
                break;
            case 14:
                this.identifierType = "PJ";
                this.minimumInstallmentValue = new BigDecimal("1000");
                this.maximumLoanValue = new BigDecimal("100000");
                break;
            case 8:
                this.identifierType = "EU";
                this.minimumInstallmentValue = new BigDecimal("100");
                this.maximumLoanValue = new BigDecimal("10000");
                break;
            case 10:
                this.identifierType = "AP";
                this.minimumInstallmentValue = new BigDecimal("400");
                this.maximumLoanValue = new BigDecimal("25000");
                break;
            default:
                throw new IllegalArgumentException("Invalid identifier length");
        }
    }
}
