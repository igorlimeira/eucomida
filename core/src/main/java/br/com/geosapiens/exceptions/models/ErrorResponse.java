package br.com.geosapiens.exceptions.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Details about the error")
public class ErrorResponse {
    @Schema(description = "Error code", example = "INTERNAL_ERROR")
    private String code;
    @Schema(description = "Description of the error", example = "An unexpected error occurred.")
    private String message;
}
