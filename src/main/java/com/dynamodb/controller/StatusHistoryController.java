package com.dynamodb.controller;

import com.dynamodb.entity.StatusHistoryEntity;
import com.dynamodb.dto.StatusHistoryDto;
import com.dynamodb.service.StatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ms-ecourt/v1/ecourt/status-history")
@RequiredArgsConstructor
public class StatusHistoryController {
    private final StatusHistoryService statusHistoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StatusHistoryEntity createStatusHistory(@RequestBody StatusHistoryDto dto) {
        return statusHistoryService.createStatusHistory(dto);
    }
}
