package org.filemanagement.filemanagerservice.communication.kafka.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.filemanagement.filemanagerservice.communication.kafka.config.KafkaConfigProps;
import org.filemanagement.filemanagerservice.communication.kafka.models.events.Event;
import org.filemanagement.filemanagerservice.communication.kafka.services.FilePublishService;
import org.filemanagement.filemanagerservice.mappers.Mapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilePublishServiceImpl implements FilePublishService {


    private final Mapper mapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConfigProps kafkaConfigProps;

    @Override
    public void publish(Event event) throws JsonProcessingException {
        log.info("inside publish method {}",event );
        final String payload = mapper.mapToString(event);

        kafkaTemplate.send(kafkaConfigProps.getTopic(), payload);

        log.info("fILE '{}' [{}] published.", event.getUsername(), event.getData());

    }
}
