package com.example.Ejer12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.Ejer12.ClientApplication;

@Repository
public interface ApplicationRepository extends JpaRepository<ClientApplication, Long> {
    Optional<ClientApplication> findByApiKey(String apiKey);
}
