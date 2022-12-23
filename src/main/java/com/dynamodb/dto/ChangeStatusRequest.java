package com.dynamodb.dto;

import com.dynamodb.model.enums.Status;
import lombok.Data;

@Data
public class ChangeStatusRequest {
    private Status status;
    private String description;
}
