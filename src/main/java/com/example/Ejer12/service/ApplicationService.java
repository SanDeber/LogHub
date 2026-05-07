package com.example.Ejer12.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.Ejer12.repository.ApplicationRepository;
import com.example.Ejer12.DTO.request.ApplicationRequestDTO;
import com.example.Ejer12.DTO.response.ApplicationResponseDTO;
import java.util.List;
import java.util.UUID;
import com.example.Ejer12.ClientApplication;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationResponseDTO register(ApplicationRequestDTO dto) {
        ClientApplication app = ClientApplication.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .email(dto.getEmail())
                .apiKey(UUID.randomUUID().toString())
                .build();

        ClientApplication saved = applicationRepository.save(app);

        return toDTO(saved);
    }

    public List<ApplicationResponseDTO> getAll() {
        return applicationRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private ApplicationResponseDTO toDTO(ClientApplication app) {
        return ApplicationResponseDTO.builder()
                .id(app.getId())
                .name(app.getName())
                .description(app.getDescription())
                .email(app.getEmail())
                .apiKey(app.getApiKey())
                .build();
    }

    public void delete(Long id) {

        applicationRepository.deleteById(id);
    }
}