package com.example.Ejer12.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.Ejer12.LogLevel;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogResponseDTO {
    private Long id;
    private String message;
    private LogLevel logLevel;
    private LocalDateTime createdAt;
    private String applicationName;
}