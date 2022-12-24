package com.dynamodb.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FilterInfo {

    private List<String> courts;
    private List<String> judges;
    private List<String> assignedPersons;

}
