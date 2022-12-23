package com.dynamodb.service;

import com.dynamodb.entity.StatusHistoryEntity;
import com.dynamodb.dto.StatusHistoryDto;
import com.dynamodb.mapper.StatusHistoryMapper;
import com.dynamodb.repository.StatusHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusHistoryService {

    private final StatusHistoryRepository statusHistoryRepository;
    public StatusHistoryEntity createStatusHistory(StatusHistoryDto dto) {
        return statusHistoryRepository.save(StatusHistoryMapper.dtoToEntity(dto));
    }
}
