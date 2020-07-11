package com.psipher.application.actions;

import com.psipher.application.exceptions.DDBException;

/**
 * Functions to generate random passwords.
 */

public interface GeneratePasswordOperations {
    /**
     * It generates a random password for users.
     * @param length Length of password
     * @return password
     */
    String generatePassword(Integer length);
}
