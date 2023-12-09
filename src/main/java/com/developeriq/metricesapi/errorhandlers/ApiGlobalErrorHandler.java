package com.developeriq.metricesapi.errorhandlers;

import com.developeriq.metricesapi.dto.ValidateTokenResponse;
import com.developeriq.metricesapi.exception.UnAuthorisedException;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
//(basePackages = {"com.developeriq.metricesapi.controller",""})
public class ApiGlobalErrorHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(WebClientResponseException.NotFound.class)
    public ServiceResponse onError(Exception ex) {
        return ServiceResponse.builder().message(ex.getMessage()).build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(WebClientResponseException.Unauthorized.class)
    public ValidateTokenResponse onAuthError(Exception ex) {
        return ValidateTokenResponse.builder().message(ex.getMessage()).build();
    }


    @Data
    @Builder
    public static class ServiceResponse {
        private String message;
    }
}
