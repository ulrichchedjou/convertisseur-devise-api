package com.KFOKAM48.Convertisseur_de_Devises.controller;

import com.KFOKAM48.Convertisseur_de_Devises.model.CurrencyConversionResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import com.KFOKAM48.Convertisseur_de_Devises.service.CurrencyConverterService;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Conversion de Devises", description = "API pour convertir des montants entre devises")
public class CurrencyConversionController {
    private final CurrencyConverterService service;

    public CurrencyConversionController(CurrencyConverterService service) {

        this.service = service;
    }

    @GetMapping("/convert")
    @Operation(summary = "Convertir un montant entre deux devises")
    public CurrencyConversionResponse convert(
            @Parameter(example = "USD") @RequestParam @Pattern(regexp = "^[A-Z]{3}$") String source,
            @Parameter(example = "EUR") @RequestParam @Pattern(regexp = "^[A-Z]{3}$") String target,
            @Parameter(example = "100") @RequestParam @DecimalMin("0.0") BigDecimal amount
    ) {
        return new CurrencyConversionResponse(source, target, amount, service.convert(source, target, amount));
    }
}