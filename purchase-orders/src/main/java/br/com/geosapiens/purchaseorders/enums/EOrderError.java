package br.com.geosapiens.purchaseorders.enums;

public enum EOrderError {
    INVALID_ORDER("INVALID_ORDER", "The order data is invalid."),
    INTERNAL_ERROR("INTERNAL_ERROR", "An unexpected error occurred.");

    private final String code;
    private final String message;

    EOrderError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}
