package com.psipher.application.api;

import com.psipher.application.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPing {
    @RequestMapping(path = "/ping", method = RequestMethod.GET)
    public User listLambdaLanguages() {
        User user = new User();
        user.setId  ("123");
        user.setName("Name");
        return user;
    }
}
