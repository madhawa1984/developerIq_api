package com.developeriq.metricesapi.authfilter;


import com.developeriq.metricesapi.exception.UnAuthorisedException;
import com.developeriq.metricesapi.services.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.client.WebClientResponseException;


import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        // This should call the auth Service end point
        final String authHeader = request.getHeader("Authorization");
        if(Optional.ofNullable(authHeader).isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // this needs corrected authenticationEntryPoint.commence
        } else {
            try {
                authService.validateToken(authHeader).block();
                filterChain.doFilter(request,response);
            } catch (WebClientResponseException.Unauthorized e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
            //update the security context holder

        }

    }
}
