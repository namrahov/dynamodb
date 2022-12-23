package com.dynamodb.mapper;


import com.dynamodb.dto.CommentDto;
import com.dynamodb.dto.DocumentDto;
import com.dynamodb.entity.ApplicationEntity;
import com.dynamodb.dto.ApplicationDto;
import com.dynamodb.entity.document.BrokenRule;
import com.dynamodb.entity.document.Comment;
import com.dynamodb.entity.document.Document;

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
        entity.setPriority(String.valueOf(dto.getPriority()));


        long commentId = 1;
        List<Comment> commentList = new ArrayList<>();
        for(CommentDto commentDto: dto.getComments()) {
            Comment comment = new Comment();
            comment.setCommentId(commentId++);
            comment.setCommentator(commentDto.getCommentator());
            comment.setDescription(commentDto.getDescription());

            commentList.add(comment);
        }

        entity.setComments(commentList);
        entity.setStatusHistoryId(dto.getStatusHistoryId());


        long documentId = 1;
        List<Document> documentList = new ArrayList<>();
        for(DocumentDto documentDto: dto.getDocuments()) {
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
}
