package com.progressSoft.Data.Warehouse.services;

import com.progressSoft.Data.Warehouse.dtos.DealRequestDto;
import com.progressSoft.Data.Warehouse.model.Deal;
import com.progressSoft.Data.Warehouse.utils.ApiCustomResponse;

import java.util.List;

public interface DealService {

    void saveDealRequests(List<DealRequestDto> dealRequestDtos);

}
