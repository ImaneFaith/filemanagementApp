package org.filemanagement.filemanagerservice.communication.kafka.models.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public  class Event {

    private String data;
    private String username;

}
