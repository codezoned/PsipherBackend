package com.psipher.application.ddbmodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

import java.util.List;

@Data
@DynamoDBTable(tableName = "UserDetails")
public class UserDDBModel {

    @DynamoDBHashKey(attributeName="userId")
    private String userId;

    @DynamoDBAttribute(attributeName = "websites")
    private List<WebsiteDDBModel> websites;

}
