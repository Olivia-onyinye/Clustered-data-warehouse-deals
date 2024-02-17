package com.progressSoft.Data.Warehouse.services;

import com.progressSoft.Data.Warehouse.dtos.DealRequestDto;
import com.progressSoft.Data.Warehouse.dtos.DealResponse;

import java.util.List;

public interface DealService {

    void saveDealRequests(List<DealRequestDto> dealRequestDtos);

    DealResponse getByUniqueId(String dealUniqueId);
}
