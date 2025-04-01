package com.KFOKAM48.Convertisseur_de_Devises.exception;

public class InvalidCurrencyException extends RuntimeException {
    public InvalidCurrencyException(String message) {
        super(message);
    }
}