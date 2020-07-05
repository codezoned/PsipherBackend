package com.psipher.application.api;

import com.psipher.application.actions.UserDetailsOperations;
import com.psipher.application.exceptions.DDBException;
import com.psipher.application.model.DeleteDomainInput;
import com.psipher.application.model.DeleteDomainOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteDomain {
    UserDetailsOperations userDetailsOperations;

    private static final Logger logger = LoggerFactory.getLogger(DeleteDomain.class);

    final static String INVALID_INPUT = "Invalid input";

    @Autowired
    public DeleteDomain(UserDetailsOperations userDetailsOperations) {
        this.userDetailsOperations = userDetailsOperations;
    }

    @RequestMapping(path = "/deletedomain", method = RequestMethod.POST)
    public DeleteDomainOutput deleteDomain(@RequestBody DeleteDomainInput deleteDomainInput) {
        if (!checkValidInput(deleteDomainInput))
            return new DeleteDomainOutput(INVALID_INPUT);
        String status;
        try {
            status = userDetailsOperations.deleteDomain(deleteDomainInput.getUserId(), deleteDomainInput.getDomain());
        } catch (DDBException e) {
            status = "Failure";
            logger.error(e.getMessage());
        }
        return new DeleteDomainOutput(status);
    }

    private boolean checkValidInput(DeleteDomainInput deleteDomainInput) {
        return deleteDomainInput != null && deleteDomainInput.getUserId() != null
                && deleteDomainInput.getDomain() != null;
    }

}
