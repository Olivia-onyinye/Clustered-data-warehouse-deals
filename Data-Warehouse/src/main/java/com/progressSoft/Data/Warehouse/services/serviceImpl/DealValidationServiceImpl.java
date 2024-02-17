package com.progressSoft.Data.Warehouse.services.serviceImpl;

import com.progressSoft.Data.Warehouse.dtos.DealRequestDto;
import com.progressSoft.Data.Warehouse.enums.CurrencyIsoCode;
import com.progressSoft.Data.Warehouse.exceptions.InvalidDealRequestException;
import com.progressSoft.Data.Warehouse.services.DealValidationService;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DealValidationServiceImpl implements DealValidationService {
    @Override
    public void validateDealRequest(DealRequestDto dealRequestDto) {
        if (StringUtils.isBlank(dealRequestDto.getDealUniqueId())) {
            throw new InvalidDealRequestException("Deal unique ID is required");
        }
        if (StringUtils.isBlank(dealRequestDto.getFromCurrency())) {
            throw new InvalidDealRequestException("Deal From Currency code is required");
        } else {
            CurrencyIsoCode.get(dealRequestDto.getFromCurrency()).orElseThrow(
                    () -> new InvalidDealRequestException("Invalid From currency ISO code"));
        }
        if (StringUtils.isBlank(dealRequestDto.getToCurrency())) {
            throw new InvalidDealRequestException("Deal To Currency code is required");
        } else {
            CurrencyIsoCode.get(dealRequestDto.getToCurrency()).orElseThrow(
                    () -> new InvalidDealRequestException("Invalid To currency ISO code"));
        }
        if (dealRequestDto.getDealAmount() == null || dealRequestDto.getDealAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidDealRequestException("Deal amount is required and must be greater than zero");
        }
    }
}
