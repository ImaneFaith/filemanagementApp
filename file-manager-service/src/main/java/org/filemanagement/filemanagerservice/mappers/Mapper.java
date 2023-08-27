package org.filemanagement.filemanagerservice.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public interface Mapper {
    public String mapToString(Object o) throws JsonProcessingException;
}
