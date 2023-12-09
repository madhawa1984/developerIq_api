package com.developeriq.metricesapi.authfilter;


import com.developeriq.metricesapi.services.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


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
            filterChain.doFilter(request,response);
        } else {
            authService.validateToken(authHeader).block();
            //update the security context holder
            filterChain.doFilter(request,response);
        }

    }
}
