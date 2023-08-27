package org.filemanagement.filemanagerservice.communication.kafka.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.filemanagement.filemanagerservice.communication.kafka.models.events.Event;

public interface FilePublishService {

    void publish(Event event) throws JsonProcessingException;
}
