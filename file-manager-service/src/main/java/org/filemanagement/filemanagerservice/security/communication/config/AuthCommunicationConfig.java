package org.filemanagement.filemanagerservice.security.communication.config;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;



@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "com.auth")
public class AuthCommunicationConfig {

    private String url;
    private String path;

}
