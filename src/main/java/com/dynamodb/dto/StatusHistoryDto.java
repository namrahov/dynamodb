package com.dynamodb.dto;

import lombok.Data;

import java.util.List;

@Data
public class StatusHistoryDto {
    private String statusHistoryId;
    private Long response;
    private String errorMessage;
    private List<BrokenRuleDto> brokenRules;
}
