package com.psipher.application.api;

import com.psipher.application.actions.UserDetailsOperations;
import com.psipher.application.exceptions.DDBException;
import com.psipher.application.model.DeleteDomainInput;
import com.psipher.application.model.DeleteDomainOutput;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.psipher.application.api.ApiConstants.INVALID_INPUT;

@RestController
public class DeleteDomain {
    UserDetailsOperations userDetailsOperations;

    private static final Logger logger = LoggerFactory.getLogger(DeleteDomain.class);


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

    // no need to null check request body since it will return to 400
    private boolean checkValidInput(DeleteDomainInput deleteDomainInput) {
        return StringUtils.isNotBlank(deleteDomainInput.getUserId()) && StringUtils.isNotBlank(deleteDomainInput.getDomain());
    }

}
