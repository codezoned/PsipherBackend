package com.psipher.application.api;

import com.psipher.application.actions.UserDetailsOperations;
import com.psipher.application.exceptions.DDBException;
import com.psipher.application.model.DeleteUserInput;
import com.psipher.application.model.DeleteUserOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteUser {
    UserDetailsOperations userDetailsOperations;

    private static final Logger logger = LoggerFactory.getLogger(DeleteUser.class);

    final static String INVALID_INPUT = "Invalid input";

    @Autowired
    public DeleteUser(UserDetailsOperations userDetailsOperations) {
        this.userDetailsOperations = userDetailsOperations;
    }

    @RequestMapping(path = "/deleteuser", method = RequestMethod.POST)
    public DeleteUserOutput deleteUser(@RequestBody DeleteUserInput deleteUserInput) {
        if (!checkValidInput(deleteUserInput)) {
            return new DeleteUserOutput(INVALID_INPUT);
        }
        String status;
        try {
            status = userDetailsOperations.deleteUser(deleteUserInput.getUserId());
        } catch (DDBException e) {
            status = "Failure";
            logger.error(e.getMessage());
        }
        return new DeleteUserOutput(status);
    }

    private boolean checkValidInput(DeleteUserInput deleteUserInput) {
        return deleteUserInput != null && deleteUserInput.getUserId() != null;
    }
}
