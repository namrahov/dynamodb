package com.dynamodb.controller;


import com.dynamodb.dto.*;
import com.dynamodb.entity.ApplicationEntity;
import com.dynamodb.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ms-ecourt/v1/ecourt/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping
    public PageableApplicationDto getApplications(@RequestParam Integer page,
                                                  @RequestParam Integer count,
                                                  @RequestParam(required = false) String courtName) {
        return applicationService.getApplications(page, count, courtName);
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping("/{applicationId}/set-deadline")
    public void setDeadline(@PathVariable String applicationId, @RequestBody DeadlineDto dto) {
        applicationService.setDeadline(applicationId, dto);
    }


}
