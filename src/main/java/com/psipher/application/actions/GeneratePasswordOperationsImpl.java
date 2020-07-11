package com.psipher.application.actions;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements GeneratePasswordOperations and provide all functionality mention in the interface.
 */
@Component
public class GeneratePasswordOperationsImpl implements GeneratePasswordOperations {

    @Override
    public String generatePassword(Integer length) {
        List<CharacterRule> rules = new ArrayList<>();
        rules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1));
        rules.add(new CharacterRule(EnglishCharacterData.LowerCase, 1));
        rules.add(new CharacterRule(EnglishCharacterData.Digit, 1));
        rules.add(new CharacterRule(new CharacterData() {

            @Override
            public String getErrorCode() {
                return "Failed to generate Special Characters";
            }

            @Override
            public String getCharacters() {
                return "!\\\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~";
            }
        }, 1));

        PasswordGenerator generator = new PasswordGenerator();
        return generator.generatePassword(length, rules);
    }
}
