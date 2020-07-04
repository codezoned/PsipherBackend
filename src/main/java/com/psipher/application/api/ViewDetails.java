package com.psipher.application.api;

import com.psipher.application.actions.UserDetailsOperations;
import com.psipher.application.ddbmodel.UserDDBModel;
import com.psipher.application.exceptions.DDBException;
import com.psipher.application.model.ViewDetailsOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewDetails {

    UserDetailsOperations userDetailsOperations;

    private static final Logger logger = LoggerFactory.getLogger(ViewDetails.class);

    @Autowired
    public ViewDetails(UserDetailsOperations userDetailsOperations) {
        this.userDetailsOperations = userDetailsOperations;
    }

    @RequestMapping(path = "/viewdetails", method = RequestMethod.GET)
    public ViewDetailsOutput viewDetails(@RequestParam String userId) {

        UserDDBModel userDDBModel;
        ViewDetailsOutput viewDetailsOutput = new ViewDetailsOutput();
        viewDetailsOutput.setUserId(userId);

        try {
            userDDBModel = userDetailsOperations.viewDetails(userId);
            if (userDDBModel != null) {
                viewDetailsOutput.setWebsites(userDDBModel.getWebsites());
                viewDetailsOutput.setStatus("success");
            } else viewDetailsOutput.setStatus("userId not present");

        } catch (DDBException e) {
            viewDetailsOutput.setStatus("Failure");
            logger.error(e.getMessage());
        }
        return viewDetailsOutput;
    }

}
