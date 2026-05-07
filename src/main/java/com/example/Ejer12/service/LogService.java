package com.example.Ejer12.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.Ejer12.DTO.request.LogRequestDTO;
import com.example.Ejer12.DTO.response.LogResponseDTO;
import java.time.LocalDateTime;
import com.example.Ejer12.Log;
import com.example.Ejer12.ClientApplication;
import com.example.Ejer12.LogLevel;
import com.example.Ejer12.repository.ApplicationRepository;
import com.example.Ejer12.repository.LogRepository;
import java.util.List;
import com.example.Ejer12.exception.ApplicationNotFoundException;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;
    private final ApplicationRepository applicationRepository;

    public LogResponseDTO register(LogRequestDTO dto) {

        ClientApplication app = applicationRepository.findById(dto.getAppId())
                .orElseThrow(() ->
                        new ApplicationNotFoundException(
                                "Aplicación no encontrada con ID: " + dto.getAppId()
                        )
                );

        Log log = Log.builder()
                .message(dto.getMessage())
                .logLevel(dto.getLogLevel())
                .createdAt(LocalDateTime.now())
                .application(app)
                .build();

        Log saved = logRepository.save(log);

        return toDTO(saved);
    }

    public List<LogResponseDTO> getLogs(
            Long appId,
            LocalDateTime from,
            LocalDateTime to,
            LogLevel level
    ) {

        List<Log> logs;

        if (appId != null && from != null && to != null) {

            logs = logRepository
                    .findByApplicationIdAndCreatedAtBetween(appId, from, to);

        } else if (appId != null) {

            logs = logRepository.findByApplicationId(appId);

        } else if (from != null && to != null) {

            logs = logRepository.findByCreatedAtBetween(from, to);

        } else if (level != null) {

            logs = logRepository.findByLogLevel(level);

        } else {

            logs = logRepository.findAll();
        }

        return logs.stream()
                .map(this::toDTO)
                .toList();
    }

    private LogResponseDTO toDTO(Log log) {

        return LogResponseDTO.builder()
                .id(log.getId())
                .message(log.getMessage())
                .logLevel(log.getLogLevel())
                .createdAt(log.getCreatedAt())
                .applicationName(log.getApplication().getName())
                .build();
    }
}