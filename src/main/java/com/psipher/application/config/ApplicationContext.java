package com.psipher.application.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.psipher.application.actions.UserDetailsOperations;
import com.psipher.application.actions.UserDetailsOperationsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContext {
    /**
     *  Creates AmazonDynamoBb client which can be used through out the application
     * @return client
     */
    @Bean
    public AmazonDynamoDB getDynamoDbClient() {
        return AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    @Bean
    public UserDetailsOperations getUserDetailsOperations() {
        return new UserDetailsOperationsImpl();
    }

    @Bean
    public DynamoDBMapper getDynamoDbMapper() {
        return new DynamoDBMapper(getDynamoDbClient());
    }

    @Bean
    public Gson getGson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

}
