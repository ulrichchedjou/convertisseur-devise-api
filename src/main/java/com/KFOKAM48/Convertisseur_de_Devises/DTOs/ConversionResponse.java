package com.KFOKAM48.Convertisseur_de_Devises.DTOs;
import java.math.BigDecimal;
public class ConversionResponse{
        private String source;
        private String target;
        private BigDecimal amount;
        private BigDecimal convertedAmount;
        private BigDecimal rate;
        // Constructor
        public ConversionResponse(String source, String target, BigDecimal amount, 
        BigDecimal convertedAmount, BigDecimal rate) {
        this.source = source;
        this.target = target;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
        this.rate = rate;
    }

    // Getters
    public String getSource() { 
        return source; 
    }
    public String getTarget() { 
        return target; 
    }
    public BigDecimal getAmount() { 
        return amount; 
    }
    public BigDecimal getConvertedAmount() {
        return convertedAmount; 
    }
    public BigDecimal getRate() { 
        return rate; 
    }
}