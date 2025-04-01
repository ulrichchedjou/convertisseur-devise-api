package com.KFOKAM48.Convertisseur_de_Devises.service;

import java.math.BigDecimal;

import com.KFOKAM48.Convertisseur_de_Devises.DTOs.ConversionResponse;
import com.KFOKAM48.Convertisseur_de_Devises.DTOs.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.KFOKAM48.Convertisseur_de_Devises.exception.ExternalApiException;
import com.KFOKAM48.Convertisseur_de_Devises.exception.InvalidCurrencyException;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;


@Service
public class CurrencyConverterService {
    private final WebClient webClient;
    private final String apiKey;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CurrencyConverterService.class);

    public CurrencyConverterService(
            WebClient webClient,
            @Value("${app.exchangerate.api.key}") String apiKey
    ) {
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    public ConversionResponse convert(String source, String target, BigDecimal amount) {
        log.info("Converting {} {} to {}", amount, source, target);

        try {
            log.debug("Making API request to: {}/{}/latest/{}", webClient.toString(), apiKey, source);

            ExchangeRateResponse response = webClient.get()
                .uri("/{apiKey}/latest/{from}", apiKey, source)
                .retrieve()
                .bodyToMono(ExchangeRateResponse.class)
                .block();

            log.debug("API response received: {}", response);

            if (response == null || response.getConversion_rates() == null) {
                log.error("API response or conversion rates is null");
                throw new ExternalApiException("Réponse API invalide ou vide");
            }

            BigDecimal rate = response.getConversion_rates().get(target);
            if (rate == null) {
                log.error("Target currency not found: {}", target);
                throw new InvalidCurrencyException("Devise cible non trouvée ou invalide: " + target);
            }

            BigDecimal convertedAmount = amount.multiply(rate);
            log.info("Conversion successful: {} {} = {} {}", amount, source, convertedAmount, target);

            return new ConversionResponse(
                    source.toUpperCase(),
                    target.toUpperCase(),
                    amount,
                    convertedAmount,
                    rate
            );

        } catch (WebClientResponseException e) {
            log.error("External API error: {}", e.getMessage(), e);
            throw new ExternalApiException("Erreur API externe : " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error during conversion: {}", e.getMessage(), e);
            throw new ExternalApiException("Erreur inattendue lors de la conversion: " + e.getMessage());
        }
    }
}