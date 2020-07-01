package com.psipher.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveDetailsInput {
    private String userId;
    private String domain;
    private String type;
    private String userAccount;
    private String password;

}
