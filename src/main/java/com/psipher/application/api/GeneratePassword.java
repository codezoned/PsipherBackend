package com.psipher.application.api;

import com.psipher.application.actions.GeneratePasswordOperations;
import com.psipher.application.model.GeneratePasswordOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneratePassword {
    GeneratePasswordOperations generatePasswordOperations;

    @Autowired
    public GeneratePassword(GeneratePasswordOperations generatePasswordOperations) {
        this.generatePasswordOperations = generatePasswordOperations;
    }

    /**
     *  We set length to be nullable so that we can return a failure response not error 400
     * @param length
     * @return
     */
    @RequestMapping(path = "/generatepassword", method = RequestMethod.GET)
    public GeneratePasswordOutput generatePassword(@RequestParam(required = false) Integer length) {
        GeneratePasswordOutput generatePasswordOutput = new GeneratePasswordOutput();
        if (length != null && length >= 6) {
            generatePasswordOutput.setPassword(generatePasswordOperations.generatePassword(length));
            generatePasswordOutput.setStatus("success");
        } else {
            generatePasswordOutput.setStatus("Failure");
            generatePasswordOutput.setPassword(null);
        }
        return generatePasswordOutput;
    }
}
