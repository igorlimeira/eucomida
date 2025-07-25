package br.com.geosapiens.exceptions.models;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TokenCreationException extends RuntimeException {

    public TokenCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
