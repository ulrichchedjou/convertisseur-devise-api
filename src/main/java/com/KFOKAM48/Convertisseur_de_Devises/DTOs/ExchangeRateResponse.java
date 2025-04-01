package com.KFOKAM48.Convertisseur_de_Devises.DTOs;

import java.math.BigDecimal;
import java.util.Map;

public class ExchangeRateResponse {
    private String result;
    private String documentation;
    private Map<String, BigDecimal> conversion_rates;

    // Getters/Setters
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public Map<String, BigDecimal> getConversion_rates() {
        return conversion_rates;
    }

    public void setConversion_rates(Map<String, BigDecimal> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }
}
