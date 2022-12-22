package com.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Account {

    String accountName;
    String balance;


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
