package com.progressSoft.Data.Warehouse.services.serviceImpl;

import com.progressSoft.Data.Warehouse.dtos.DealRequestDto;
import com.progressSoft.Data.Warehouse.exceptions.DataIntegrityViolationException;
import com.progressSoft.Data.Warehouse.exceptions.DealRequestAlreadyExistException;
import com.progressSoft.Data.Warehouse.model.Deal;
import com.progressSoft.Data.Warehouse.repository.DealRepository;
import com.progressSoft.Data.Warehouse.services.DealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DealServiceImpl implements DealService {
    private static final Logger logger = LoggerFactory.getLogger(DealServiceImpl.class);

    private final DealRepository dealRepository;

    @Autowired
    public DealServiceImpl(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }
    public void saveDealRequest(DealRequestDto dealRequestDto) {
        boolean existingDealRequest = dealRepository.existsByDealUniqueId(dealRequestDto.getDealUniqueId());
        if (existingDealRequest) {
            String errorMessage = "Deal request with the same unique ID already exists";
            logger.error(errorMessage);
            throw new DealRequestAlreadyExistException(errorMessage);
        }
        Deal deal = new Deal();
        deal.setDealUniqueId(dealRequestDto.getDealUniqueId());
        deal.setFromCurrency(dealRequestDto.getFromCurrency());
        deal.setToCurrency(dealRequestDto.getToCurrency());
        deal.setDealTimestamp(LocalDateTime.now());
        deal.setDealAmount(dealRequestDto.getDealAmount());
        dealRepository.save(deal);
        logger.info("Deal request has been saved successfully: {}", dealRequestDto.getDealUniqueId());
    }

    @Override
    public void saveDealRequests(List<DealRequestDto> dealRequestDtos) {
        for (DealRequestDto dealRequestDto1 : dealRequestDtos) {
            try {
                saveDealRequest(dealRequestDto1);
            } catch (DealRequestAlreadyExistException e) {
                logger.error("Deal request with the same unique ID already exists: {}", e.getMessage());
                throw new DealRequestAlreadyExistException("Deal request with the same unique ID already exists");
            } catch (DataIntegrityViolationException e) {
                    logger.error("Data integrity violation occurred: {}", e.getMessage());
        } catch (Exception e) {
                logger.error("An Error occurred while saving deal request: {}", e.getMessage());
            }
        }
    }
}
