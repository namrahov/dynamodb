package com.dynamodb.dto;

import lombok.Data;

@Data
public class BrokenRuleDto {
    private Long brokenRuleId;

    private String member;

    private String message;
}
