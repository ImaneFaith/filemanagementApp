package org.filemanagement.notificationservice.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.filemanagement.notificationservice.events.Event;

import java.util.Optional;

public interface Mapper {
    public String mapToString(Object o) throws JsonProcessingException;
    public Event mapToEvent(String s) throws JsonProcessingException;
}
