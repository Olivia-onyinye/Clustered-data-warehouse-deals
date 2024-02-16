package com.progressSoft.Data.Warehouse.services.serviceImpl;

import com.progressSoft.Data.Warehouse.dtos.DealRequestDto;
import com.progressSoft.Data.Warehouse.enums.CurrencyIsoCode;
import com.progressSoft.Data.Warehouse.exceptions.DataIntegrityViolationException;
import com.progressSoft.Data.Warehouse.exceptions.DealRequestAlreadyExistException;
import com.progressSoft.Data.Warehouse.model.Deal;
import com.progressSoft.Data.Warehouse.repository.DealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class DealServiceImplTest {

    @Mock
    private  DealRepository dealRepository;
    @InjectMocks
    private DealServiceImpl dealService;
    private DealRequestDto dealRequestDto;
    private Deal deal;
    private DealRequestDto dealRequestDto2;
    private List<DealRequestDto> dealRequestDtos;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dealRequestDto = new DealRequestDto("DR001", CurrencyIsoCode.USD, CurrencyIsoCode.NGN, 1000.00);
        deal = new Deal(1L, "DR001", CurrencyIsoCode.USD, CurrencyIsoCode.NGN, LocalDateTime.now(), 1000.00);
        dealRequestDto2 = new DealRequestDto("DR002", CurrencyIsoCode.USD, CurrencyIsoCode.NGN, 2000.00);
        dealRequestDtos = new ArrayList<>();
        dealRequestDtos.add(dealRequestDto);
        when(dealRepository.save(deal)).thenReturn(deal);
    }



    @Test
    void testSuccessfulSaveDealRequest() {
        when(dealRepository.existsByDealUniqueId(anyString())).thenReturn(false);
        assertDoesNotThrow(() -> dealService.saveDealRequest(dealRequestDto));
        verify(dealRepository, times(1)).save(any(Deal.class));
    }

    @Test
    public void testSaveDealRequest_Success() {
        dealService.saveDealRequest(dealRequestDto);
        verify(dealRepository, times(1)).existsByDealUniqueId("DR001");
        verify(dealRepository, times(1)).save(any(Deal.class));
    }
    @Test
    void testSaveDealRequest_AlreadyExists() {
        when(dealRepository.existsByDealUniqueId(dealRequestDto.getDealUniqueId())).thenReturn(true);
        assertThrows(DealRequestAlreadyExistException.class, () -> dealService.saveDealRequest(dealRequestDto));
        verify(dealRepository, never()).save(any(Deal.class));
        verify(dealRepository, times(1)).existsByDealUniqueId(dealRequestDto.getDealUniqueId());
    }
    @Test
    void testSaveDealRequests_HandleDataIntegrityViolationException() {
        List<DealRequestDto> dealRequestDtos = List.of(new DealRequestDto());
        doThrow(new DataIntegrityViolationException("constraint [unique_deal_unique_id]"))
                .when(dealRepository).save(any(Deal.class));
        dealService.saveDealRequests(dealRequestDtos);
        verify(dealRepository, times(1)).save(any(Deal.class));
    }
    @Test
    public void testSaveDealRequests_HandleOtherKindsOfExceptions() {
        List<DealRequestDto> dealRequestDtos = List.of(new DealRequestDto());
        doThrow(new RuntimeException("An Error occurred while saving deal request: "))
                .when(dealRepository).save(any(Deal.class));
        dealService.saveDealRequests(dealRequestDtos);
        verify(dealRepository, times(1)).save(any(Deal.class));
    }

    @Test
    public void testSaveDealRequests_HandleOtherExceptions() {
        List<DealRequestDto> dealRequestDtos = List.of(new DealRequestDto());
        doThrow(new RuntimeException("Data integrity violation occurred: "))
                .when(dealRepository).save(any(Deal.class));
        dealService.saveDealRequests(dealRequestDtos);
        verify(dealRepository, times(1)).save(any(Deal.class));
    }

}