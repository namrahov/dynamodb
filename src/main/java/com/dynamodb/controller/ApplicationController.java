package com.dynamodb.controller;


import com.dynamodb.entity.ApplicationEntity;
import com.dynamodb.dto.ApplicationDto;
import com.dynamodb.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ms-ecourt/v1/ecourt/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationEntity createApplication(@RequestBody ApplicationDto dto) {
        return applicationService.createApplication(dto);
    }

    /*@PutMapping("/{id}/change-status")
    fun changeStatus(@RequestHeader(USER_ID) userId: Long,
                     @PathVariable id: Long,
                     @RequestBody request: ChangeStatusRequest) = applicationService.changeStatus(userId, id, request)*/


}