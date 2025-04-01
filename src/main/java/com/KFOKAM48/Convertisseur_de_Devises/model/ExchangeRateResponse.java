// This class has been moved to the DTOs package
// See com.KFOKAM48.Convertisseur_de_Devises.DTOs.ExchangeRateResponse
// This file is kept as a placeholder to avoid compilation errors in case it's referenced elsewhere
package com.KFOKAM48.Convertisseur_de_Devises.model;

import lombok.Data;
import java.util.Map;
import java.math.BigDecimal;

@Data
@Deprecated
public class ExchangeRateResponse {
    private String base_code;
    private Map<String, BigDecimal> conversion_rates;
}
