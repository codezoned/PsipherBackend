package com.psipher.application.api;


import com.psipher.application.actions.PasswordPawnedOperations;
import com.psipher.application.model.PasswordPawnedOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordPawnedChecker {
    private boolean status;
    PasswordPawnedOperations passwordpawnedoperations;
    @Autowired
    public PasswordPawnedChecker(PasswordPawnedOperations passwordpawnedoperations) {
        this.passwordpawnedoperations = passwordpawnedoperations;
    }
    @RequestMapping(path = "/passwordpawnedchecker", method = RequestMethod.GET)
    public PasswordPawnedOutput passwordpawnedoutput(@RequestParam String pass) {
        return passwordpawnedoperations.passwordpawnedchecker(pass);
        }
}