package com.psipher.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserAccountInput {
    private String userId;
    private String domain;
    private String userAccount;
}
