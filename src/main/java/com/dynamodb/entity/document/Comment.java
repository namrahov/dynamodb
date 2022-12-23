package com.dynamodb.entity.document;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.dynamodb.model.enums.CommentType;
import lombok.Getter;
import lombok.Setter;

@DynamoDBDocument
@Getter
@Setter
public class Comment {
    private Long commentId;

    private String commentator;

    private String description;

    @DynamoDBTypeConvertedEnum
    private CommentType commentType;
}
