package com.example.Ejer12.filter;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import java.io.IOException;
import com.example.Ejer12.repository.ApplicationRepository;
import org.springframework.web.filter.OncePerRequestFilter; 

@Component
@RequiredArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

    private final ApplicationRepository applicationRepository;

    @Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain) throws ServletException, IOException {

    String uri = request.getRequestURI();
    String method = request.getMethod();

    if (method.equals("POST") && uri.equals("/api/logs")) {

        String apiKey = request.getHeader("X-API-KEY");

        if (apiKey == null || apiKey.isBlank()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"API Key ausente\"}");
            return;
        }

        boolean exists = applicationRepository.findByApiKey(apiKey).isPresent();

        if (!exists) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"API Key inválida\"}");
            return;
        }
    }

    filterChain.doFilter(request, response);
}

}