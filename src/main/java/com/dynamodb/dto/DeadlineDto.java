package com.dynamodb.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DeadlineDto {
    private LocalDate deadline;
}
