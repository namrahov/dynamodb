package com.dynamodb.entity.document;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Data;

@DynamoDBDocument
@Data
public class BrokenRule {

    private Long brokenRuleId;

    private String member;

    private String message;

}
