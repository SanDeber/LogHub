package com.example.Ejer12.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.Ejer12.service.ApplicationService;
import com.example.Ejer12.DTO.request.ApplicationRequestDTO;
import com.example.Ejer12.DTO.response.ApplicationResponseDTO;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@Tag(name = "Applications", description = "Gestión de aplicaciones registradas en LogHub")

public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    @Operation(summary = "Registrar una nueva aplicación",
            description = "Registra una aplicación y genera automáticamente una API Key")
    @ApiResponse(responseCode = "201", description = "Aplicación registrada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<ApplicationResponseDTO> register(@Valid @RequestBody ApplicationRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicationService.register(dto));
    }

    @GetMapping
    @Operation(summary = "Listar todas las aplicaciones",
            description = "Devuelve todas las aplicaciones registradas")
    @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente")
    public ResponseEntity<List<ApplicationResponseDTO>> getAll() {

        return ResponseEntity.ok(applicationService.getAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar aplicación")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {

        applicationService.delete(id);

        return ResponseEntity.noContent().build();
    }
}