package com.progressSoft.Data.Warehouse.dtos;

import com.progressSoft.Data.Warehouse.enums.CurrencyIsoCode;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@ToString
public class DealRequestDto {

    @NotBlank(message = "Deal unique Id is required")
    private String dealUniqueId;

    @NotNull(message = "From Currency ISO Code is required")
    @Enumerated(EnumType.STRING)
    private CurrencyIsoCode fromCurrency;

    @NotNull(message = "To Currency ISO Code is required")
    @Enumerated(EnumType.STRING)
    private CurrencyIsoCode toCurrency;

    @NotNull(message = "Deal timestamp is required")
    private LocalDateTime dealTimestamp;

    @NotNull(message = "Deal amount is required")
    private Double dealAmount;


    public DealRequestDto() {
    }

    public DealRequestDto(String dealUniqueId, CurrencyIsoCode fromCurrency, CurrencyIsoCode toCurrency, LocalDateTime dealTimestamp, Double dealAmount) {
        this.dealUniqueId = dealUniqueId;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.dealTimestamp = dealTimestamp;
        this.dealAmount = dealAmount;
    }

    public String getDealUniqueId() {
        return dealUniqueId;
    }

    public void setDealUniqueId(String dealUniqueId) {
        this.dealUniqueId = dealUniqueId;
    }

    public CurrencyIsoCode getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(CurrencyIsoCode fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public CurrencyIsoCode getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(CurrencyIsoCode toCurrency) {
        this.toCurrency = toCurrency;
    }

    public LocalDateTime getDealTimestamp() {
        return dealTimestamp;
    }

    public void setDealTimestamp(LocalDateTime dealTimestamp) {
        this.dealTimestamp = dealTimestamp;
    }

    public Double getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(Double dealAmount) {
        this.dealAmount = dealAmount;
    }
}
