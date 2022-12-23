package com.dynamodb.service;

import com.dynamodb.entity.ApplicationEntity;
import com.dynamodb.dto.ApplicationDto;
import com.dynamodb.mapper.ApplicationMapper;
import com.dynamodb.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    public ApplicationEntity createApplication(ApplicationDto dto) {
        return applicationRepository.save(ApplicationMapper.dtoToEntity(dto));
    }

}
