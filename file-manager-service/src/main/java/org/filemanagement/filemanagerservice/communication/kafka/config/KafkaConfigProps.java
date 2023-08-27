package org.filemanagement.filemanagerservice.communication.kafka.config;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties("kafka.config")
public class KafkaConfigProps {

    private String topic;
}
