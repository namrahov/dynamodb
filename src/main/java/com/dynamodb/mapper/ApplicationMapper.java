package com.dynamodb.mapper;


import com.dynamodb.dto.CommentDto;
import com.dynamodb.dto.DocumentDto;
import com.dynamodb.entity.ApplicationEntity;
import com.dynamodb.dto.ApplicationDto;
import com.dynamodb.entity.document.BrokenRule;
import com.dynamodb.entity.document.Comment;
import com.dynamodb.entity.document.Document;
import com.dynamodb.model.enums.CustomerType;
import com.dynamodb.model.enums.Priority;
import com.dynamodb.model.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApplicationMapper {
    public static ApplicationEntity dtoToEntity(ApplicationDto dto) {
        ApplicationEntity entity = new ApplicationEntity();

        entity.setRequestId(dto.getRequestId());
        entity.setCheckedId(dto.getCheckedId());
        entity.setPerson(dto.getPerson());
        entity.setCustomerType(dto.getCustomerType());
        entity.setCustomerName(dto.getCustomerName());
        entity.setFilePath(dto.getFilePath());
        entity.setCourtName(dto.getCourtName());
        entity.setJudgeName(dto.getJudgeName());
        entity.setDecisionNumber(dto.getDecisionNumber());
        entity.setNote(dto.getNote());
        entity.setStatus(dto.getStatus());
        entity.setBeginDate(dto.getBeginDate());
        entity.setEndDate(dto.getEndDate());
        entity.setDecisionDate(dto.getDecisionDate());
        entity.setIsChecked(dto.getIsChecked());
        entity.setMailSent(dto.getMailSent());
        entity.setDeadline(dto.getDeadline());
        entity.setAssigneeId(dto.getAssigneeId());
        entity.setAssigneeName(dto.getAssigneeName());
        entity.setPriority(dto.getPriority());


        long commentId = 1;
        List<Comment> commentList = new ArrayList<>();
        for (CommentDto commentDto : dto.getComments()) {
            Comment comment = new Comment();
            comment.setCommentId(commentId++);
            comment.setCommentator(commentDto.getCommentator());
            comment.setDescription(commentDto.getDescription());
            comment.setCommentType(commentDto.getCommentType());

            commentList.add(comment);
        }

        entity.setComments(commentList);
        entity.setStatusHistoryId(dto.getStatusHistoryId());


        long documentId = 1;
        List<Document> documentList = new ArrayList<>();
        for (DocumentDto documentDto : dto.getDocuments()) {
            Document document = new Document();
            document.setDocumentId(documentId++);
            document.setDocumentStatus(documentDto.getDocumentStatus());
            document.setRequestType(documentDto.getRequestType());
            document.setDescription(documentDto.getDescription());

            documentList.add(document);
        }

        entity.setDocuments(documentList);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());


        return entity;
    }


    public static List<ApplicationDto> entitiesToDtos(List<ApplicationEntity> entities) {
        List<ApplicationDto> dtos = new ArrayList<>();

        for(ApplicationEntity entity: entities) {
            ApplicationDto dto = new ApplicationDto();
            dto.setApplicationId(entity.getApplicationId());
            dto.setRequestId(entity.getRequestId());
            dto.setCheckedId(entity.getCheckedId());
            dto.setPerson(entity.getPerson());
            dto.setCustomerType(entity.getCustomerType());
            dto.setCustomerName(entity.getCustomerName());
            dto.setFilePath(entity.getFilePath());
            dto.setCourtName(entity.getCourtName());
            dto.setJudgeName(entity.getJudgeName());
            dto.setDecisionNumber(entity.getDecisionNumber());
            dto.setNote(entity.getNote());
            dto.setStatus(entity.getStatus());
            dto.setBeginDate(entity.getBeginDate());
            dto.setEndDate(entity.getEndDate());
            dto.setDecisionDate(entity.getDecisionDate());
            dto.setIsChecked(entity.getIsChecked());
            dto.setMailSent(entity.getMailSent());
            dto.setDeadline(entity.getDeadline());
            dto.setAssigneeId(entity.getAssigneeId());
            dto.setAssigneeName(entity.getAssigneeName());
            dto.setPriority(entity.getPriority());


            List<CommentDto> commentDtos = new ArrayList<>();

            for (Comment comment : entity.getComments()) {
                CommentDto commentDto = new CommentDto();
                commentDto.setCommentId(comment.getCommentId());
                commentDto.setCommentator(comment.getCommentator());
                commentDto.setDescription(comment.getDescription());
                commentDto.setCommentType(comment.getCommentType());

                commentDtos.add(commentDto);
            }

            dto.setComments(commentDtos);


            dto.setStatusHistoryId(entity.getStatusHistoryId());


            List<DocumentDto> documentDtos = new ArrayList<>();

            for (Document document: entity.getDocuments()) {
                DocumentDto documentDto = new DocumentDto();
                documentDto.setDocumentId(document.getDocumentId());
                documentDto.setRequestType(document.getRequestType());
                documentDto.setDescription(document.getDescription());
                documentDto.setDocumentStatus(document.getDocumentStatus());

                documentDtos.add(documentDto);
            }

            dto.setDocuments(documentDtos);

            dto.setCreatedAt(entity.getCreatedAt());
            dto.setUpdatedAt(entity.getUpdatedAt());

            dtos.add(dto);
        }

        return dtos;
    }
}
