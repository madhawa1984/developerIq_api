package com.developeriq.metricesapi.services;


import com.developeriq.metricesapi.dto.ValidateTokenRequest;
import com.developeriq.metricesapi.dto.ValidateTokenResponse;
import com.developeriq.metricesapi.exception.UnAuthorisedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.developeriq.metricesapi.constant.Constant.*;


@Service
public class AuthService {
    @Autowired
    @Qualifier("authServiceClient")
    private WebClient webClient;

    public Mono<Boolean> validateToken(String token) {
        return webClient.post().uri(uriBuilder -> uriBuilder.path("/api/validateToken").build())
                .headers(httpHeaders -> {
                    httpHeaders.add(ACCEPT_HEADER, JSON_TYPE);
                    httpHeaders.add(CONTENT_TYPE,JSON_TYPE);
                }).bodyValue(ValidateTokenRequest.builder().token(token).build())
                .retrieve().bodyToMono(ValidateTokenResponse.class)
                .map(ValidateTokenResponse::isValid)
                .onErrorResume(throwable -> Mono.error(new UnAuthorisedException("")));
                /*.onErrorResume(throwable -> {
                    System.out.println("Error is happening");
                    throwable.printStackTrace();
                    return Mono.just(Boolean.FALSE);
                });*/
                /*doOnError(throwable -> {
                    System.out.println("Error is happening");
                    throwable.printStackTrace();
                    throw new UnAuthorisedException("InvalidToken");
                });
                */

    }

}
