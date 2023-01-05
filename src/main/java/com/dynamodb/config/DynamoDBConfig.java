package com.dynamodb.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.dynamodb.repository")
public class DynamoDBConfig {

    @Value("${aws.access-key}")
    private String accessKey;

    @Value("${aws.secret-key}")
    private String secretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {

        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(Regions.EU_NORTH_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        return amazonDynamoDB;
    }

    @PostConstruct
    private void postConstruct() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(Regions.EU_NORTH_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
        DynamoDB dynamoDB = new DynamoDB(client);




        List<AttributeDefinition> attributeDefinitionsEmployee = new ArrayList<>();
        attributeDefinitionsEmployee.add(new AttributeDefinition().withAttributeName("id").withAttributeType("S"));

        List<KeySchemaElement> keySchemaEmployee = new ArrayList<>();
        keySchemaEmployee.add(new KeySchemaElement().withAttributeName("id").withKeyType(KeyType.HASH));

        CreateTableRequest requestEmployee = new CreateTableRequest()
                .withTableName("employee")
                .withKeySchema(keySchemaEmployee)
                .withAttributeDefinitions(attributeDefinitionsEmployee)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(6L));



        List<AttributeDefinition> attributeDefinitionsApplication = new ArrayList<>();
        attributeDefinitionsApplication.add(new AttributeDefinition().withAttributeName("ApplicationId").withAttributeType("S"));

        List<KeySchemaElement> keySchemaApplication = new ArrayList<>();
        keySchemaApplication.add(new KeySchemaElement().withAttributeName("ApplicationId").withKeyType(KeyType.HASH));

        CreateTableRequest requestApplication = new CreateTableRequest()
                .withTableName("application")
                .withKeySchema(keySchemaApplication)
                .withAttributeDefinitions(attributeDefinitionsApplication)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(6L));



        List<AttributeDefinition> attributeDefinitionsStatusHistory = new ArrayList<>();
        attributeDefinitionsStatusHistory.add(new AttributeDefinition().withAttributeName("StatusHistoryId").withAttributeType("S"));

        List<KeySchemaElement> keySchemaStatusHistory = new ArrayList<>();
        keySchemaStatusHistory.add(new KeySchemaElement().withAttributeName("StatusHistoryId").withKeyType(KeyType.HASH));

        CreateTableRequest requestStatusHistory = new CreateTableRequest()
                .withTableName("status_history")
                .withKeySchema(keySchemaStatusHistory)
                .withAttributeDefinitions(attributeDefinitionsStatusHistory)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(6L));



        List<AttributeDefinition> attributeDefinitionsMovie = new ArrayList<>();
        attributeDefinitionsMovie.add(new AttributeDefinition().withAttributeName("filmId").withAttributeType("S"));

        List<KeySchemaElement> keySchemaMovie = new ArrayList<>();
        keySchemaMovie.add(new KeySchemaElement().withAttributeName("filmId").withKeyType(KeyType.HASH));

        CreateTableRequest requestMovie = new CreateTableRequest()
                .withTableName("hesen")
                .withKeySchema(keySchemaMovie)
                .withAttributeDefinitions(attributeDefinitionsMovie)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(6L));



        List<AttributeDefinition> attributeDefinitionsApp = new ArrayList<>();
        attributeDefinitionsApp.add(new AttributeDefinition().withAttributeName("id").withAttributeType("S"));

        List<KeySchemaElement> keySchemaApp = new ArrayList<>();
        keySchemaApp.add(new KeySchemaElement().withAttributeName("id").withKeyType(KeyType.HASH));

        CreateTableRequest requestApp = new CreateTableRequest()
                .withTableName("app")
                .withKeySchema(keySchemaApp)
                .withAttributeDefinitions(attributeDefinitionsApp)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(6L));


        Table table;

        try {
            if (!isTableExist(dynamoDB.getTable("employee"))) {
                table = dynamoDB.createTable(requestEmployee);
                table.waitForActive();
            }
            if (!isTableExist(dynamoDB.getTable("application"))) {
                table = dynamoDB.createTable(requestApplication);
                table.waitForActive();
            }
            if (!isTableExist(dynamoDB.getTable("status_history"))) {
                table = dynamoDB.createTable(requestStatusHistory);
                table.waitForActive();
            }
            if (!isTableExist(dynamoDB.getTable("hesen"))) {
                table = dynamoDB.createTable(requestMovie);
                table.waitForActive();
            }
            if (!isTableExist(dynamoDB.getTable("app"))) {
                table = dynamoDB.createTable(requestApp);
                table.waitForActive();
            }

        } catch (InterruptedException e) {
            System.out.println("postConstruct InterruptedException");
        }
    }

    public static boolean isTableExist(Table table) {
        try {
            table.describe();
        } catch (ResourceNotFoundException e) {
            return false;
        }
        return true;
    }

}
