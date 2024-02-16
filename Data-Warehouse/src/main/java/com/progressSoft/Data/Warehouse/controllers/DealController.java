package com.progressSoft.Data.Warehouse.controllers;

import com.progressSoft.Data.Warehouse.dtos.DealRequestDto;
import com.progressSoft.Data.Warehouse.services.DealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/deals")
public class DealController {
    private final DealService dealService;
    @Autowired
    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping("/requests")
    public ResponseEntity<String> saveDealRequests(@Valid @RequestBody List<DealRequestDto> dealRequestDtos){
        dealService.saveDealRequests(dealRequestDtos);
        return new ResponseEntity<>("Deal request has been accepted and saved successfully", HttpStatus.CREATED);
    }
}
