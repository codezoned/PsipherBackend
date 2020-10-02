package com.psipher.application.actions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

class GeneratePasswordOperationsImplTest {

    GeneratePasswordOperationsImpl generatePasswordOperations =  new GeneratePasswordOperationsImpl();

    @Test
    public void shouldGeneratePasswordWithGivenLength() {
        String generatedPassword = generatePasswordOperations.generatePassword(7);
        Assertions.assertEquals(7, generatedPassword.length());
    }

    @Test
    public void shouldContainAtLeastOneUpperCaseAndOneLowerCaseAndOneNumberInGeneratedPassword() {
        Pattern passwordRules = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
        String generatedPassword = generatePasswordOperations.generatePassword(7);
        Assertions.assertTrue(passwordRules.matcher(generatedPassword).matches());
    }
}