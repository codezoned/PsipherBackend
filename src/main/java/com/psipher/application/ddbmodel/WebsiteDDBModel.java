package com.psipher.application.ddbmodel;

import   com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@DynamoDBDocument
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WebsiteDDBModel {

    @EqualsAndHashCode.Include
    @DynamoDBAttribute(attributeName="domain")
    private String domain;

    @DynamoDBAttribute(attributeName="userAccounts")
    private List<UserAccountDDBModel> userAccountsDDBModel;

}
  