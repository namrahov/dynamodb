package com.dynamodb.dto;

import com.dynamodb.model.enums.DocumentStatus;
import com.dynamodb.model.enums.RequestType;
import lombok.Data;

@Data
public class DocumentDto {
    private Long documentId;

    private RequestType requestType;

    private String description;

    private DocumentStatus documentStatus = DocumentStatus.WAITING;
}
