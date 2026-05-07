package com.example.Ejer12.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import com.example.Ejer12.DTO.request.LogRequestDTO;
import com.example.Ejer12.DTO.response.LogResponseDTO;
import com.example.Ejer12.service.LogService;
import com.example.Ejer12.LogLevel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
@Tag(name = "Logs", description = "Gestión de logs de las aplicaciones")
public class LogController {

    private final LogService logService;

    @PostMapping
    @Operation(summary = "Registrar un log",
            description = "Registra un log. Requiere la cabecera X-API-KEY válida")
    @ApiResponse(responseCode = "201", description = "Log registrado exitosamente")
    @ApiResponse(responseCode = "401", description = "API Key inválida o ausente")
    @ApiResponse(responseCode = "404", description = "Aplicación no encontrada")
    @Parameter(name = "X-API-KEY", description = "API Key de la aplicación", required = true, in = ParameterIn.HEADER)
    public ResponseEntity<LogResponseDTO> register(
            @Parameter(hidden = true) @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @Valid @RequestBody LogRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(logService.register(dto));
    }

    @GetMapping
    @Operation(summary = "Listar logs",
            description = "Devuelve logs con filtros opcionales")
    @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente")
    public ResponseEntity<List<LogResponseDTO>> getLogs(

            @RequestParam(required = false)
            Long appId,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime from,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime to,

            @RequestParam(required = false)
            LogLevel level
    ) {

        return ResponseEntity.ok(
                logService.getLogs(appId, from, to, level)  
        );
    }
}