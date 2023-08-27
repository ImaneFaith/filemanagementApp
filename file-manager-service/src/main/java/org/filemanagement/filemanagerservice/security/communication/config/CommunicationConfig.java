package org.filemanagement.filemanagerservice.security.communication.config;

import lombok.RequiredArgsConstructor;
import org.filemanagement.filemanagerservice.security.communication.services.AuthenticationCommunicationService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(AuthCommunicationConfig.class)
@RequiredArgsConstructor
@Configuration
public class CommunicationConfig {

    private final AuthCommunicationConfig config;
    @Bean
    public AuthenticationCommunicationService auth(){
        return new AuthenticationCommunicationService(config);
    }
}
