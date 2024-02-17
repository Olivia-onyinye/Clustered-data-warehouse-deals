package com.progressSoft.Data.Warehouse.services.serviceImpl;

import com.progressSoft.Data.Warehouse.dtos.DealRequestDto;
import com.progressSoft.Data.Warehouse.enums.CurrencyIsoCode;
import com.progressSoft.Data.Warehouse.exceptions.InvalidDealRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DealValidationServiceImplTest {


    private final DealValidationServiceImpl validationService = new DealValidationServiceImpl();
    private DealRequestDto dealRequestDto;

    @BeforeEach
    void setUp() {
        dealRequestDto = new DealRequestDto("DR001", "USD", "ngn", new BigDecimal("1000.00"));

    }

    @Test
    void validateDealRequest() {
        assertDoesNotThrow(() -> validationService.validateDealRequest(dealRequestDto));
    }

    @Test
    void validateDealRequest_BlankFromCurrency_ThrowsException() {
        DealRequestDto request = new DealRequestDto("DR001", "", CurrencyIsoCode.ARS.name(), BigDecimal.TEN);
        assertThrows(InvalidDealRequestException.class, () -> validationService.validateDealRequest(request));
    }

    @Test
    void validateDealRequest_NullToCurrency_ThrowsException() {
        DealRequestDto request = new DealRequestDto("DR001", CurrencyIsoCode.ARS.name(), "", BigDecimal.TEN);
        assertThrows(InvalidDealRequestException.class, () -> validationService.validateDealRequest(request));
    }
    @Test
    void validateDealRequest_BlankDealUniqueId_ThrowsException() {
        DealRequestDto request = new DealRequestDto(null, CurrencyIsoCode.USD.name(), CurrencyIsoCode.NGN.name(), BigDecimal.TEN);
        assertThrows(InvalidDealRequestException.class, () -> validationService.validateDealRequest(request));
    }

    @Test
    void validateDealRequest_InvalidFromCurrency_ThrowsException() {
        DealRequestDto request = new DealRequestDto("DR002", "INV", CurrencyIsoCode.NGN.name(), BigDecimal.TEN);
        assertThrows(InvalidDealRequestException.class, () -> validationService.validateDealRequest(request));
    }

    @Test
    void validateDealRequest_InvalidToCurrency_ThrowsException() {
        DealRequestDto request = new DealRequestDto("DR003", CurrencyIsoCode.USD.name(), "ARR", BigDecimal.TEN);
        assertThrows(InvalidDealRequestException.class, () -> validationService.validateDealRequest(request));
    }

    @Test
    void validateDealRequest_NegativeDealAmount_ThrowsException() {
        DealRequestDto request = new DealRequestDto("DR004", CurrencyIsoCode.USD.name(), CurrencyIsoCode.GBP.name(), BigDecimal.valueOf(-10));
        assertThrows(InvalidDealRequestException.class, () -> validationService.validateDealRequest(request));
    }
}