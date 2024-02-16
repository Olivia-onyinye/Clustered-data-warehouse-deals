package com.progressSoft.Data.Warehouse.model;

import com.progressSoft.Data.Warehouse.enums.CurrencyIsoCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Builder
@Entity
@Table(name = "deal_requests")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "Deal unique Id is required")
    @Column(name = "deal_unique_id", unique = true)
    private String dealUniqueId;

    @Column(name = "from_currency_iso_code", nullable = false)
    @NotNull(message = "From Currency ISO Code is required")
    @Enumerated(EnumType.STRING)
    private CurrencyIsoCode fromCurrency;
    @Column(name = "to_currency_iso_code", nullable = false)
    @NotNull(message = "To Currency ISO Code is required")
    @Enumerated(EnumType.STRING)
    private CurrencyIsoCode toCurrency;
    @Column(name="deal_timestamp")
    @CreationTimestamp
    private LocalDateTime dealTimestamp;

    @Column(name = "deal_amount", nullable = false)
    @NotNull(message = "Deal amount is required")
    private Double dealAmount;

    public Deal() {
    }

    public Deal(Long id, String dealUniqueId, CurrencyIsoCode fromCurrency, CurrencyIsoCode toCurrency, LocalDateTime dealTimestamp, Double dealAmount) {
        Id = id;
        this.dealUniqueId = dealUniqueId;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.dealTimestamp = dealTimestamp;
        this.dealAmount = dealAmount;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
