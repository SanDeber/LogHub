package com.example.Ejer12.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.example.Ejer12.LogLevel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogRequestDTO {

    @NotBlank(message = "El mensaje es obligatorio")
    private String message;

    @NotNull(message = "El nivel de log es obligatorio")
    private LogLevel logLevel;

    @NotNull(message = "El ID de la aplicación es obligatorio")
    private Long appId;
}
