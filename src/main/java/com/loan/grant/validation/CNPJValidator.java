package com.loan.grant.validation;

import java.util.InputMismatchException;

public class CNPJValidator {

    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || cnpj.matches(cnpj.charAt(0) + "{14}")) {
            return false;
        }

        try {
            char dig13 = calculateCheckDigit(cnpj, 12);
            char dig14 = calculateCheckDigit(cnpj, 13);

            return (dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13));
        } catch (InputMismatchException e) {
            return false;
        }
    }

    private static char calculateCheckDigit(String cnpj, int length) {
        int[] weights = (length == 12) ? new int[]{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2} : new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * weights[i];
        }
        int mod = 11 - (sum % 11);
        return (mod == 10 || mod == 11) ? '0' : (char) (mod + '0');
    }
}

