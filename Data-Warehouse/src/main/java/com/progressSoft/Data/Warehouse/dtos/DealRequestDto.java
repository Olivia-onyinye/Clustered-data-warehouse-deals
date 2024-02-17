package com.progressSoft.Data.Warehouse.dtos;

import com.progressSoft.Data.Warehouse.enums.CurrencyIsoCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@ToString
public class DealRequestDto {

    @NotBlank(message = "Deal unique Id is required")
    private String dealUniqueId;
    private String fromCurrency;
    private String toCurrency;
    @NotNull(message = "Deal amount is required")
    private BigDecimal dealAmount;



    public DealRequestDto() {
    }

    public DealRequestDto(String dealUniqueId, String fromCurrency, String toCurrency, BigDecimal dealAmount) {
        this.dealUniqueId = dealUniqueId;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.dealAmount = dealAmount;
    }

    public String getDealUniqueId() {
        return dealUniqueId;
    }

    public void setDealUniqueId(String dealUniqueId) {
        this.dealUniqueId = dealUniqueId;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public BigDecimal getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
    }
}
