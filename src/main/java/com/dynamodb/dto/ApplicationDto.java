package com.dynamodb.dto;

import com.dynamodb.model.enums.CustomerType;
import com.dynamodb.model.enums.Priority;
import com.dynamodb.model.enums.Status;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApplicationDto {
    private String applicationId;

    private Long requestId;

    private Long checkedId;

    private String person;

    private CustomerType customerType;

    private String customerName;

    private String filePath;

    private String courtName;

    private String judgeName;

    private String decisionNumber;

    private String note;

    private Status status;

    private LocalDateTime beginDate;

    private LocalDateTime endDate;

    private LocalDateTime decisionDate;

    private Boolean isChecked;

    private Boolean mailSent;

    private LocalDate deadline;

    private Long assigneeId;

    private String assigneeName;

    private Priority priority;

    private List<CommentDto> comments;

    private String statusHistoryId;

    private List<DocumentDto> documents;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
