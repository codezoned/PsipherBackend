package com.psipher.application.api;

import com.psipher.application.actions.UserDetailsOperations;
import com.psipher.application.exceptions.DDBException;
import com.psipher.application.model.DeleteUserAccountInput;
import com.psipher.application.model.DeleteUserAccountOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteUserAccount {
    UserDetailsOperations userDetailsOperations;

    private static final Logger logger = LoggerFactory.getLogger(DeleteUserAccount.class);

    final static String INVALID_INPUT = "Invalid input";

    @Autowired
    public DeleteUserAccount(UserDetailsOperations userDetailsOperations) {
        this.userDetailsOperations = userDetailsOperations;
    }

    @RequestMapping(path = "/deleteuseraccount", method = RequestMethod.POST)
    public DeleteUserAccountOutput deleteUserAccount(@RequestBody DeleteUserAccountInput deleteUserAccountInput) {
        if (!checkValidInput(deleteUserAccountInput))
            return new DeleteUserAccountOutput(INVALID_INPUT);
        String status;
        try {
            status = userDetailsOperations.deleteUserAccount(deleteUserAccountInput.getUserId(), deleteUserAccountInput.getDomain(), deleteUserAccountInput.getUserAccount());
        } catch (DDBException e) {
            status = "Failure";
            logger.error(e.getMessage());
        }
        return new DeleteUserAccountOutput(status);
    }

    private boolean checkValidInput(DeleteUserAccountInput deleteUserAccountInput) {
        return deleteUserAccountInput != null && deleteUserAccountInput.getUserId() != null
                && deleteUserAccountInput.getDomain() != null && deleteUserAccountInput.getUserAccount() != null;
    }
}
