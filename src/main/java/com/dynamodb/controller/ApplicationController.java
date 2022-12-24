package com.dynamodb.controller;


import com.dynamodb.dto.ChangeStatusRequest;
import com.dynamodb.dto.FilterInfo;
import com.dynamodb.dto.PageableApplicationDto;
import com.dynamodb.entity.ApplicationEntity;
import com.dynamodb.dto.ApplicationDto;
import com.dynamodb.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/ms-ecourt/v1/ecourt/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping
    public PageableApplicationDto getApplications(@RequestParam Integer page, @RequestParam Integer count) {
        return applicationService.getApplications(page, count);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationEntity createApplication(@RequestBody ApplicationDto dto) {
        return applicationService.createApplication(dto);
    }

    @PutMapping("/{applicationId}/change-status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeStatus(@PathVariable String applicationId, @RequestBody ChangeStatusRequest request) {
        applicationService.changeStatus(applicationId, request);
    }


    @GetMapping("/filter-info")
    public FilterInfo getFilterInfo() {
        return applicationService.getFilterInfo();
    }

}
