package org.filemanagement.notificationservice.config.fcm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "as.firebase")
public class FirebaseProperties {

    private Resource accountService;
}
