package com.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

import java.util.List;

@DynamoDBDocument
public class Actors {

    List<String> actors;

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }
}
