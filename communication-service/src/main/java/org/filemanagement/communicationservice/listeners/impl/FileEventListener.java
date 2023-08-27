package org.filemanagement.communicationservice.listeners.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.filemanagement.communicationservice.listeners.EventListener;
import org.filemanagement.communicationservice.publishers.EventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileEventListener implements EventListener {

    private final EventPublisher eventPublisher;

    @KafkaListener(topics = {"file-publish"})
    @Override
    public String listens(String event) {

        log.info("fILE '{}' received.", event);
       eventPublisher.publish(event);
       return event;
    }
}
