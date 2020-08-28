package com.psipher.application.actions;


import com.github.nbaars.pwnedpasswords4j.client.PwnedPasswordChecker;
import com.github.nbaars.pwnedpasswords4j.client.PwnedPasswordClient;
import com.psipher.application.model.PasswordPawnedOutput;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implements PasswordPawnedCheckerOperations and provide all functionality mentioned in the interface.
 */
@Component
public class PasswordPawnedOperationsImpl implements PasswordPawnedOperations {
    private PwnedPasswordChecker checker;

    @Autowired
    public PasswordPawnedOperationsImpl(PwnedPasswordChecker checker){
        this.checker=checker;
    }
    @Override
    public boolean passwordPawnedChecker(String pass) {
        return checker.check(pass);
    }
}

