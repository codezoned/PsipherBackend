package com.psipher.application.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.AttributeEncryptor;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.encryption.DynamoDBEncryptor;
import com.amazonaws.services.dynamodbv2.datamodeling.encryption.providers.DirectKmsMaterialProvider;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.psipher.application.actions.UserDetailsOperations;
import com.psipher.application.actions.UserDetailsOperationsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContext {
    /**
     * Creates AmazonDynamoBb client which can be used through out the application
     *
     * @return client
     */
    @Bean
    public AmazonDynamoDB getDynamoDbClient() {
        return AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    @Bean
    public AWSKMS getAwsKms() {
        return AWSKMSClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    }

    @Bean
    public DirectKmsMaterialProvider getDirectKmsMaterialProvider() {
        final String cmkArn = "alias/encryptAll";
        return new DirectKmsMaterialProvider(getAwsKms(), cmkArn);
    }

    @Bean
    public DynamoDBEncryptor getEncryptor() {
        return DynamoDBEncryptor.getInstance(getDirectKmsMaterialProvider());
    }

    @Bean
    public DynamoDBMapperConfig getMapperConfig() {
        return DynamoDBMapperConfig.builder().withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.CLOBBER).build();
    }

    @Bean
    public DynamoDBMapper getDynamoDbMapper() {
        return new DynamoDBMapper(getDynamoDbClient(), getMapperConfig(), new AttributeEncryptor(getEncryptor()));
    }

    @Bean
    public Gson getGson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }
}
