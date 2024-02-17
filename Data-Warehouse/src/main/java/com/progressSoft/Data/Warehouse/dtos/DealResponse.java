package com.progressSoft.Data.Warehouse.dtos;

import com.progressSoft.Data.Warehouse.model.Deal;

import java.math.BigDecimal;

public class DealResponse {

    private String dealUniqueId;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal dealAmount;

    public DealResponse(String dealUniqueId, String fromCurrency, String toCurrency, BigDecimal dealAmount) {
        this.dealUniqueId = dealUniqueId;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.dealAmount = dealAmount;
    }

    public DealResponse() {
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


    public static DealResponse of(Deal response) {
        DealResponse dealResponse = new DealResponse();
        dealResponse.setDealUniqueId(response.getDealUniqueId());
        dealResponse.setFromCurrency(response.getFromCurrency().name());
        dealResponse.setToCurrency(response.getToCurrency().name());
        dealResponse.setDealAmount(response.getDealAmount());
        return dealResponse;
    }
}
