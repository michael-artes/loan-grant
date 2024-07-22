package com.loan.grant.validation;


import org.springframework.stereotype.Component;

@Component
public class IdentifierValidator {

    public boolean isValid(String identifier, String identifierType) {
        switch (identifierType) {
            case "PF":
                return isValidCPF(identifier);
            case "PJ":
                return isValidCNPJ(identifier);
            case "EU":
                return isValidUniversityStudent(identifier);
            case "AP":
                return isValidRetired(identifier);
            default:
                throw new IllegalArgumentException("Unknown identifier type");
        }
    }

    private boolean isValidCPF(String cpf) {
        return CPFValidator.isValidCPF(cpf);
    }

    private boolean isValidCNPJ(String cnpj) {
        return CNPJValidator.isValidCNPJ(cnpj);
    }

    private boolean isValidUniversityStudent(String identifier) {
        if (identifier.length() != 8) {
            return false;
        }
        int firstDigit = Character.getNumericValue(identifier.charAt(0));
        int lastDigit = Character.getNumericValue(identifier.charAt(7));
        return (firstDigit + lastDigit) == 9;
    }

    private boolean isValidRetired(String identifier) {
        if (identifier.length() != 10) {
            return false;
        }
        char lastDigit = identifier.charAt(9);
        for (int i = 0; i < 9; i++) {
            if (identifier.charAt(i) == lastDigit) {
                return false;
            }
        }
        return true;
    }
}

