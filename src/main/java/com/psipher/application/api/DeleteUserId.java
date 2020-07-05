package com.psipher.application.api;

import com.psipher.application.actions.UserDetailsOperations;
import com.psipher.application.exceptions.DDBException;
import com.psipher.application.model.DeleteUserIdInput;
import com.psipher.application.model.DeleteUserIdOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteUserId {
    UserDetailsOperations userDetailsOperations;

    private static final Logger logger = LoggerFactory.getLogger(DeleteUserId.class);

    final static String INVALID_INPUT = "Invalid input";

    @Autowired
    public DeleteUserId(UserDetailsOperations userDetailsOperations) {
        this.userDetailsOperations = userDetailsOperations;
    }

    @RequestMapping(path = "/deleteuserid", method = RequestMethod.POST)
    public DeleteUserIdOutput deleteUserIdOutput(@RequestBody DeleteUserIdInput deleteUserIdInput) {
        if (!checkValidInput(deleteUserIdInput)) {
            return new DeleteUserIdOutput(INVALID_INPUT);
        }
        String status;
        try {
            status = userDetailsOperations.deleteUserId(deleteUserIdInput.getUserId());
        } catch (DDBException e) {
            status = "Failure";
            logger.error(e.getMessage());
        }
        return new DeleteUserIdOutput(status);
    }

    private boolean checkValidInput(DeleteUserIdInput deleteUserIdInput) {
        return deleteUserIdInput != null && deleteUserIdInput.getUserId() != null;
    }
}
