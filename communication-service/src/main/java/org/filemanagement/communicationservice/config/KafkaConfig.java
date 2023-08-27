package org.filemanagement.communicationservice.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic fileNotificationTopic(final KafkaConfigProps kafkaConfigProps){

        return TopicBuilder.name(kafkaConfigProps.getTopic())
                .replicas(1)
                .build();

    }

}
