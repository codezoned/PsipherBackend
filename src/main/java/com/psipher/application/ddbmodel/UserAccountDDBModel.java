package com.psipher.application.ddbmodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@DynamoDBDocument
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserAccountDDBModel {

    @EqualsAndHashCode.Include
    @DynamoDBAttribute(attributeName="id")
    private String id;

    @DynamoDBAttribute(attributeName="type")
    private String type;

    @DynamoDBAttribute(attributeName="password")
    private String password;
}
