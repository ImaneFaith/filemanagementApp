package org.filemanagement.notificationservice.config.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.RequiredArgsConstructor;
import org.filemanagement.notificationservice.config.fcm.FirebaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(FirebaseProperties.class)
public class FmcConfig {
    private final FirebaseProperties firebaseProperties;

    @Bean
    FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp){
        return FirebaseMessaging.getInstance(firebaseApp);
    }

    @Bean
    FirebaseApp firebaseApp(GoogleCredentials credentials){
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    GoogleCredentials googleCredentials() {
        try {
            if (firebaseProperties.getAccountService() != null) {
                try( InputStream is = firebaseProperties.getAccountService().getInputStream()) {
                    return GoogleCredentials.fromStream(is);
                }
            }
            else {
                // Use standard credentials chain. Useful when running inside GKE
                return GoogleCredentials.getApplicationDefault();
            }
        }
        catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
