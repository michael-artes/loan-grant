package com.loan.grant.validation;

import java.util.InputMismatchException;

public class CPFValidator {

    public static boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches(cpf.charAt(0) + "{11}")) {
            return false;
        }

        try {
            char dig10 = calculateCheckDigit(cpf, 10);
            char dig11 = calculateCheckDigit(cpf, 11);

            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
        } catch (InputMismatchException e) {
            return false;
        }
    }

    private static char calculateCheckDigit(String cpf, int length) {
        int sum = 0, weight = length;
        for (int i = 0; i < length - 1; i++) {
            sum += (cpf.charAt(i) - 48) * weight--;
        }
        int mod = 11 - (sum % 11);
        return (mod == 10 || mod == 11) ? '0' : (char) (mod + 48);
    }
}

