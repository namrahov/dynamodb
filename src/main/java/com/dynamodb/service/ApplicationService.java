package com.dynamodb.service;

import com.dynamodb.dto.ChangeStatusRequest;
import com.dynamodb.entity.ApplicationEntity;
import com.dynamodb.dto.ApplicationDto;
import com.dynamodb.entity.document.Comment;
import com.dynamodb.mapper.ApplicationMapper;
import com.dynamodb.model.enums.CommentType;
import com.dynamodb.model.enums.Status;
import com.dynamodb.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationEntity getApplicationById(String applicationId) {
        Optional<ApplicationEntity> optionalApplicationEntity = applicationRepository.findById(applicationId);
        ApplicationEntity applicationEntity;
        if(optionalApplicationEntity.isPresent()) {
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
        if(optionalApplicationEntity.isPresent()) {
            applicationEntity = optionalApplicationEntity.get();
        } else {
            throw new RuntimeException("Application is not found");
        }

        if (request.getStatus() == Status.HOLD) {
            long size = applicationEntity.getComments().size();
            applicationEntity.getComments()
                    .add(new Comment(++size,"Nurlan",request.getDescription(), CommentType.INTERNAL));
        }

        applicationEntity.setStatus(request.getStatus());

        ApplicationEntity savedApplication = applicationRepository.save(applicationEntity);
        System.out.println(savedApplication);
    }

    /*
     @Transactional
    fun changeStatus(userId: Long, id: Long, request: ChangeStatusRequest) {
        log.info("ActionLog.changeStatus.start applicationId: $id")

        val user = adminClient.getUserById(userId)
        val application = getApplicationIfExist(id)

        if (request.status == Status.HOLD) {
            commentRepository.save(buildCommentEntity(user, request.description, application))
        }

        validateApplicationStatus(application.status, request.status)

        application.status = request.status
        val savedApplication = applicationRepository.save(application)

        mailSender.sendToEmailQueue(buildMailDto(
                 savedApplication,
                 mailUtil.buildRequestedInformations(savedApplication),
                 corporateEmail)
        )

        log.info("ActionLog.changeStatus.success applicationId: $id")
    }
     */
}
