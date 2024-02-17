package com.progressSoft.Data.Warehouse.services.serviceImpl;

import com.progressSoft.Data.Warehouse.dtos.DealRequestDto;
import com.progressSoft.Data.Warehouse.enums.CurrencyIsoCode;
import com.progressSoft.Data.Warehouse.exceptions.DealRequestAlreadyExistException;
import com.progressSoft.Data.Warehouse.exceptions.InvalidDealRequestException;
import com.progressSoft.Data.Warehouse.model.Deal;
import com.progressSoft.Data.Warehouse.repository.DealRepository;
import com.progressSoft.Data.Warehouse.services.DealValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DealServiceImplTest {

    @Mock
    private  DealRepository dealRepository;
    @Mock
    private DealValidationService validationService;
    @InjectMocks
    private DealServiceImpl dealService;
    private DealRequestDto dealRequestDto;
    private Deal deal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dealRequestDto = new DealRequestDto("DR001", "USD", "ngn", new BigDecimal("1000.00"));
        deal = new Deal(1L, "DR001", CurrencyIsoCode.USD, CurrencyIsoCode.NGN, LocalDateTime.now(), new BigDecimal("1000.00"));
        List<DealRequestDto> dealRequestDtos = new ArrayList<>();
        dealRequestDtos.add(dealRequestDto);
        when(dealRepository.save(deal)).thenReturn(deal);
    }

    @Test
    void saveDealRequests_SuccessfulExecution() {
        when(dealRepository.existsByDealUniqueId("DR001")).thenReturn(false);
        assertDoesNotThrow(() -> dealService.saveDealRequests(Collections.singletonList(dealRequestDto)));
        verify(validationService, times(1)).validateDealRequest(dealRequestDto);
        verify(dealRepository, times(1)).existsByDealUniqueId("DR001");
        verify(dealRepository, times(1)).save(any(Deal.class));
    }

    @Test
    void saveDealRequests_DealRequestAlreadyExists() {
        when(dealRepository.existsByDealUniqueId("DR001")).thenReturn(true);
        DealRequestAlreadyExistException exception = assertThrows(DealRequestAlreadyExistException.class,
                () -> dealService.saveDealRequests(Collections.singletonList(dealRequestDto)));
        assertEquals("This deal request cannot be saved as it already exists", exception.getMessage());
        verify(validationService, times(1)).validateDealRequest(dealRequestDto);
        verify(dealRepository, times(1)).existsByDealUniqueId("DR001");
        verify(dealRepository, never()).save(any());
    }
    @Test
    void getDealByUniqueId_DealExists_ReturnsDealResponse() {
        String dealUniqueId = "DR001";
        deal.setDealUniqueId(dealUniqueId);
        when(dealRepository.findByDealUniqueId(dealUniqueId)).thenReturn(Optional.of(deal));
        var response = dealService.getByUniqueId(dealUniqueId);
        assertEquals(dealUniqueId, response.getDealUniqueId());
        verify(dealRepository, times(1)).findByDealUniqueId(dealUniqueId);
    }

    @Test
    void getDealByUniqueId_DealDoesNotExist_ThrowsException() {
        String dealUniqueId = "DR004";
        when(dealRepository.findByDealUniqueId("DR004")).thenReturn(Optional.empty());
        InvalidDealRequestException exception = assertThrows(InvalidDealRequestException.class, () -> dealService.getByUniqueId(dealUniqueId));
        assertEquals("This deal request does not exist", exception.getMessage());
        verify(dealRepository, times(1)).findByDealUniqueId(dealUniqueId);
    }

}