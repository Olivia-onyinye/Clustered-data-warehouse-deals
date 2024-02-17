package com.progressSoft.Data.Warehouse.services;

import com.progressSoft.Data.Warehouse.dtos.DealRequestDto;

public interface DealValidationService {
    void validateDealRequest(DealRequestDto dealRequestDto);
}
