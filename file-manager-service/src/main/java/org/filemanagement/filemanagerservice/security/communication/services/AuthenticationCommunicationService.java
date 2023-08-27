package org.filemanagement.filemanagerservice.security.communication.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.filemanagement.filemanagerservice.security.SecurityConstants;
import org.filemanagement.filemanagerservice.security.communication.config.AuthCommunicationConfig;
import org.filemanagement.filemanagerservice.security.communication.models.AuthenticationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@EnableConfigurationProperties(AuthCommunicationConfig.class)
public class AuthenticationCommunicationService {

    private final WebClient webClient;
    private final AuthCommunicationConfig authCommunicationConfig;



    public AuthenticationCommunicationService(AuthCommunicationConfig authCommunicationConfig) {
        this.webClient = WebClient.builder().baseUrl(authCommunicationConfig.getUrl()).build();
        this.authCommunicationConfig = authCommunicationConfig;
    }





    public Mono<AuthenticationValidation> validateToken(String token){
        //log.info("calling authentication service to validate token >>>");
        return this.webClient.get().uri(authCommunicationConfig.getPath()).header(SecurityConstants.HEADER, String.format("Bearer %s", token))
                            .retrieve().bodyToMono(AuthenticationValidation.class);
    }
}
