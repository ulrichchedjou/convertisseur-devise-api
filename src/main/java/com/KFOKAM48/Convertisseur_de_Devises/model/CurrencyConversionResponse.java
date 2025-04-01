package com.KFOKAM48.Convertisseur_de_Devises.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import com.KFOKAM48.Convertisseur_de_Devises.DTOs.ConversionResponse;

@Data
@Getter
@Setter
public class CurrencyConversionResponse {
    private String source;
    private String target;
    private BigDecimal amount;
    private BigDecimal convertedAmount;
    private BigDecimal rate;

    // Constructor
    public CurrencyConversionResponse(String source, String target, BigDecimal amount, ConversionResponse conversionResponse) {
        this.source = source;
        this.target = target;
        this.amount = amount;
        this.convertedAmount = conversionResponse.getConvertedAmount();
        this.rate = conversionResponse.getRate();
    }

    // Default constructor
    public CurrencyConversionResponse() {
    }

    // Getters and Setters
    public String getSource() {
        return source;
    }

    public void setSourceCurrency(String source) {
        this.source = source;
    }

    public String getTargetCurrency() {
        return target;
    }

    public void setTargetCurrency(String target) {
        this.target = target;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}