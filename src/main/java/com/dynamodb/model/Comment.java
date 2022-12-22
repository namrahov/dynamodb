package com.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Comment {
    String commentator;
    String description;

    public String getCommentator() {
        return commentator;
    }

    public void setCommentator(String commentator) {
        this.commentator = commentator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentator='" + commentator + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
