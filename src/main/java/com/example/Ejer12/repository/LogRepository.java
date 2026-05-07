package com.example.Ejer12.repository;

import com.example.Ejer12.Log;
import com.example.Ejer12.LogLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findByApplicationId(Long appId);

    List<Log> findByCreatedAtBetween(
            LocalDateTime from,
            LocalDateTime to
    );

    List<Log> findByApplicationIdAndCreatedAtBetween(
            Long appId,
            LocalDateTime from,
            LocalDateTime to
    );

    List<Log> findByLogLevel(LogLevel level);
}