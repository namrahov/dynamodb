package com.dynamodb.service;

import com.dynamodb.config.DynamoDBConfig;
import com.dynamodb.dto.ChangeStatusRequest;
import com.dynamodb.dto.FilterInfo;
import com.dynamodb.dto.PageableApplicationDto;
import com.dynamodb.entity.ApplicationEntity;
import com.dynamodb.dto.ApplicationDto;
import com.dynamodb.entity.document.Comment;
import com.dynamodb.mapper.ApplicationMapper;
import com.dynamodb.model.App;
import com.dynamodb.model.Employee;
import com.dynamodb.model.enums.CommentType;
import com.dynamodb.model.enums.Status;
import com.dynamodb.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public PageableApplicationDto getApplications(Integer page, Integer count, String courtName) {
        Pageable pageable = PageRequest.of(page, count);

        Page<ApplicationEntity> pages;
        if(courtName == null) {
             pages = applicationRepository.findAll(pageable);
        } else {
             pages = applicationRepository.findByCourtName(courtName, pageable);
        }

        var applications = pages.getContent();

        var lastPageNumber = pages.getTotalPages();

        var totalElements = pages.getTotalElements();

        if (lastPageNumber != 0) lastPageNumber -= 1;

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
        List<ApplicationEntity> lastSixMonthApplications = applicationRepository.findByCreatedAtBetween(
                LocalDateTime.now().minusMonths(6),
                LocalDateTime.now()
        );

        //Application with max request id
       /* ApplicationEntity max =
                lastSixMonthApplications.stream()
                .max(Comparator.comparing(ApplicationEntity::getRequestId))
                .orElseThrow(NoSuchElementException::new);

        System.out.println(max.getRequestId());*/

        List<ApplicationEntity> distinctByCourNames = lastSixMonthApplications.stream()
                .filter(distinctByKey(ApplicationEntity::getCourtName)).toList();
        List<ApplicationEntity> distinctByJudgeNames = lastSixMonthApplications.stream()
                .filter(distinctByKey(ApplicationEntity::getJudgeName)).toList();
        List<ApplicationEntity> distinctByAssignedPersons = lastSixMonthApplications.stream()
                .filter(distinctByKey(ApplicationEntity::getAssigneeName)).toList();


        List<String> courts = distinctByCourNames.stream().map(ApplicationEntity::getCourtName).toList();
        List<String> judges = distinctByJudgeNames.stream().map(ApplicationEntity::getJudgeName).toList();
        List<String> assignedPersons = distinctByAssignedPersons.stream().map(ApplicationEntity::getAssigneeName).toList();

        return FilterInfo.builder()
                .courts(courts)
                .judges(judges)
                .assignedPersons(assignedPersons)
                .build();
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }



}
