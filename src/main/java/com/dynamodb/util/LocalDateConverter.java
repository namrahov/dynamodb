package com.dynamodb.util;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.LocalDate;

public class LocalDateConverter implements DynamoDBTypeConverter<String, LocalDate> {
    @Override
    public String convert(final LocalDate localDate) {
        return localDate.toString();
    }

    @Override
    public LocalDate unconvert(final String stringValue) {
        return LocalDate.parse(stringValue);
    }
}

