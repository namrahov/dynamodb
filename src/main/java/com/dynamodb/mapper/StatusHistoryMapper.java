package com.dynamodb.mapper;

import com.dynamodb.entity.StatusHistoryEntity;
import com.dynamodb.entity.document.BrokenRule;
import com.dynamodb.dto.BrokenRuleDto;
import com.dynamodb.dto.StatusHistoryDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StatusHistoryMapper {

    public static StatusHistoryEntity dtoToEntity(StatusHistoryDto dto) {
        StatusHistoryEntity entity = new StatusHistoryEntity();

        entity.setResponse(dto.getResponse());
        entity.setErrorMessage(dto.getErrorMessage());

        long brokenRuleId = 1;
        List<BrokenRule> brokenRuleList = new ArrayList<>();
        for(BrokenRuleDto brokenRuleDto: dto.getBrokenRules()) {
            BrokenRule brokenRule = new BrokenRule();
            brokenRule.setBrokenRuleId(brokenRuleId++);
            brokenRule.setMember(brokenRuleDto.getMember());
            brokenRule.setMessage(brokenRuleDto.getMessage());

            brokenRuleList.add(brokenRule);
        }

        entity.setBrokenRules(brokenRuleList);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        return entity;
    }
}
