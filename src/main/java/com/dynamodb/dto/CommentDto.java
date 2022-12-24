package com.dynamodb.dto;

import com.dynamodb.model.enums.CommentType;
import lombok.Data;

@Data
public class CommentDto {
    private Long commentId;
    private String commentator;
    private String description;
    private CommentType commentType;
}
