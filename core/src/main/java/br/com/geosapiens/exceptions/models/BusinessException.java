package br.com.geosapiens.exceptions.models;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class BusinessException extends RuntimeException implements Serializable {
    private final String code;

    public BusinessException(String message, String code) {
        super(message);
        this.code = code;
    }

}
