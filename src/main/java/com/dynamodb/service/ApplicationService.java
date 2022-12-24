package com.dynamodb.service;

import com.dynamodb.config.DynamoDBConfig;
import com.dynamodb.dto.ChangeStatusRequest;
import com.dynamodb.dto.FilterInfo;
import com.dynamodb.dto.PageableApplicationDto;
import com.dynamodb.entity.ApplicationEntity;
import com.dynamodb.dto.ApplicationDto;
import com.dynamodb.entity.document.Comment;
import com.dynamodb.mapper.ApplicationMapper;
import com.dynamodb.model.enums.CommentType;
import com.dynamodb.model.enums.Status;
import com.dynamodb.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public PageableApplicationDto getApplications(Integer page, Integer count) {
        Pageable pageable = PageRequest.of(page, count);

        var pages = applicationRepository.findAll(pageable);

        var applications = pages.getContent();

        var lastPageNumber = pages.getTotalPages();

        var totalElements = pages.getTotalElements();

        if (lastPageNumber != 0) lastPageNumber -= 1;


        for (ApplicationEntity application : applications) {
            System.out.println(application);
        }

        return PageableApplicationDto.builder()
                .list(ApplicationMapper.entitiesToDtos(applications))
                .hasNextPage(pages.hasNext())
                .lastPageNumber(lastPageNumber)
                .totalCount(totalElements)
                .build();
    }

    public ApplicationEntity getApplicationById(String applicationId) {
        Optional<ApplicationEntity> optionalApplicationEntity = applicationRepository.findById(applicationId);
        ApplicationEntity applicationEntity;
        if (optionalApplicationEntity.isPresent()) {
            applicationEntity = optionalApplicationEntity.get();
        } else {
            throw new RuntimeException("Application is not found");
        }

        return applicationEntity;
    }

    public ApplicationEntity createApplication(ApplicationDto dto) {
        return applicationRepository.save(ApplicationMapper.dtoToEntity(dto));
    }

    public void changeStatus(String applicationId, ChangeStatusRequest request) {
        Optional<ApplicationEntity> optionalApplicationEntity = applicationRepository.findById(applicationId);
        ApplicationEntity applicationEntity;
        if (optionalApplicationEntity.isPresent()) {
            applicationEntity = optionalApplicationEntity.get();
        } else {
            throw new RuntimeException("Application is not found");
        }

        if (request.getStatus() == Status.HOLD) {
            long size = applicationEntity.getComments().size();
            applicationEntity.getComments()
                    .add(new Comment(++size, "Nurlan", request.getDescription(), CommentType.INTERNAL));
        }

        applicationEntity.setStatus(request.getStatus());

        ApplicationEntity savedApplication = applicationRepository.save(applicationEntity);
        System.out.println(savedApplication);
    }

    public FilterInfo getFilterInfo() {
        List<ApplicationEntity> applicationEntities
                = (List<ApplicationEntity>) applicationRepository.findAll();

        for (ApplicationEntity application : applicationEntities) {
            System.out.println(application);
        }

        ApplicationEntity low =
                applicationEntities.stream()
                .max(Comparator.comparing(ApplicationEntity::getRequestId))
                .orElseThrow(NoSuchElementException::new);

        //System.out.println(low.getRequestId());

        List<String> courts = new ArrayList<>();
        List<String> judges = new ArrayList<>();
        List<String> assignedPersons = new ArrayList<>();


        FilterInfo filterInfo = new FilterInfo();
        filterInfo.setCourts(courts);
        filterInfo.setJudges(judges);
        filterInfo.setAssignedPersons(assignedPersons);

        return filterInfo;
    }


}
