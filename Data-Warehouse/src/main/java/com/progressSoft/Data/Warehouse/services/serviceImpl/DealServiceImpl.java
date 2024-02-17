package com.progressSoft.Data.Warehouse.services.serviceImpl;

import com.progressSoft.Data.Warehouse.dtos.DealRequestDto;
import com.progressSoft.Data.Warehouse.dtos.DealResponse;
import com.progressSoft.Data.Warehouse.enums.CurrencyIsoCode;
import com.progressSoft.Data.Warehouse.exceptions.DealRequestAlreadyExistException;
import com.progressSoft.Data.Warehouse.exceptions.InvalidDealRequestException;
import com.progressSoft.Data.Warehouse.model.Deal;
import com.progressSoft.Data.Warehouse.repository.DealRepository;
import com.progressSoft.Data.Warehouse.services.DealService;
import com.progressSoft.Data.Warehouse.services.DealValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DealServiceImpl implements DealService {
    private static final Logger logger = LoggerFactory.getLogger(DealServiceImpl.class);
    private final DealValidationService validationService;
    private final DealRepository dealRepository;

    @Autowired
    public DealServiceImpl(DealValidationService validationService, DealRepository dealRepository) {
        this.validationService = validationService;
        this.dealRepository = dealRepository;
    }

    @Override
    public void saveDealRequests(List<DealRequestDto> dealRequestDtos) {
        for (DealRequestDto dealRequestDto : dealRequestDtos) {
                validationService.validateDealRequest(dealRequestDto);
                boolean existingDealRequest = dealRepository.existsByDealUniqueId(dealRequestDto.getDealUniqueId());
                if (existingDealRequest) {
                    logger.error("Deal request could not be created as it already exists");
                    throw new DealRequestAlreadyExistException("This deal request cannot be saved as it already exists");
                } else {
                    saveDealRequest(dealRequestDto);
                }
        }
    }

    @Override
    public DealResponse getByUniqueId(String dealUniqueId) {
        var response = dealRepository.findByDealUniqueId(dealUniqueId)
                .orElseThrow(()-> new InvalidDealRequestException("This deal request does not exist"));
        return DealResponse.of(response);
    }


    private void saveDealRequest(DealRequestDto dealRequestDto) {
        Deal deal = new Deal();
        deal.setDealUniqueId(dealRequestDto.getDealUniqueId());
        deal.setFromCurrency(CurrencyIsoCode.get(dealRequestDto.getFromCurrency()).get());
        deal.setToCurrency(CurrencyIsoCode.get(dealRequestDto.getToCurrency()).get());
        deal.setDealTimestamp(LocalDateTime.now());
        deal.setDealAmount(dealRequestDto.getDealAmount());
        dealRepository.save(deal);
    }
}
