package org.filemanagement.communicationservice.publishers.impl;

import lombok.RequiredArgsConstructor;
import org.filemanagement.communicationservice.config.KafkaConfigProps;
import org.filemanagement.communicationservice.publishers.EventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaEventPublisher implements EventPublisher {


    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConfigProps kafkaConfigProps;

    @Override
    public void publish(String event) {
        kafkaTemplate.send(kafkaConfigProps.getTopic(),event);
    }
}
