package com.dynamodb.entity.document;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.dynamodb.model.enums.DocumentStatus;
import com.dynamodb.model.enums.RequestType;
import lombok.Data;

@DynamoDBDocument
@Data
public class Document {
    private Long documentId;

    @DynamoDBTypeConvertedEnum
    private RequestType requestType;

    private String description;

    @DynamoDBTypeConvertedEnum
    private DocumentStatus documentStatus;
}
