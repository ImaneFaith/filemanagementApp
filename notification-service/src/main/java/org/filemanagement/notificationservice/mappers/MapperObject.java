package org.filemanagement.notificationservice.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.filemanagement.notificationservice.events.Event;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
@Slf4j
public class MapperObject implements Mapper{

    private final ObjectMapper mapper;

    @Override
    public String mapToString(Object o) throws JsonProcessingException {

             return mapper.writeValueAsString(o);
    }

    @Override
    public Event mapToEvent(String s) throws JsonProcessingException{

            return mapper.readValue(s, Event.class);

    }




}
