package com.psipher.application.api;

import com.google.gson.Gson;
import com.psipher.application.actions.UserDetailsOperations;
import com.psipher.application.model.SaveDetailsInput;
import com.psipher.application.model.SaveDetailsOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveDetails {

    UserDetailsOperations userDetailsOperations;

    Gson gson;

    private static final Logger logger = LoggerFactory.getLogger(SaveDetails.class);

    final static String INVALID_INPUT = "Invalid input";
    final static String UNKNOWN_USER = "Unknown User";

    @Autowired
    public SaveDetails(UserDetailsOperations userDetailsOperations, Gson gson) {
        this.userDetailsOperations = userDetailsOperations;
        this.gson = gson;
    }

    @RequestMapping(path = "/savedetails", method = RequestMethod.POST)
    public SaveDetailsOutput saveDetails(@RequestBody SaveDetailsInput saveDetailsInput) {
        if (!checkValidInput(saveDetailsInput))
            return new SaveDetailsOutput(saveDetailsInput!=null ?saveDetailsInput.getUserId():UNKNOWN_USER, INVALID_INPUT);
        String status = userDetailsOperations
                .saveDetails(saveDetailsInput.getUserId(), saveDetailsInput.getDomain(),
                        saveDetailsInput.getUserAccount(), saveDetailsInput.getType(),
                        saveDetailsInput.getPassword());
        SaveDetailsOutput saveDetailsOutput = new SaveDetailsOutput();
        saveDetailsOutput.setUserId(saveDetailsInput.getUserId());
        saveDetailsOutput.setStatus(status);
        logger.info(gson.toJson(saveDetailsOutput));
        return saveDetailsOutput;
    }

    private boolean checkValidInput(SaveDetailsInput saveDetailsInput) {
        return saveDetailsInput != null && saveDetailsInput.getUserId() != null
                && saveDetailsInput.getDomain() != null && saveDetailsInput.getPassword() != null
                && saveDetailsInput.getUserAccount() != null;
    }


}
