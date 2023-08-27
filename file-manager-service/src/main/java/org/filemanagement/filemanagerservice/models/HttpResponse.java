package org.filemanagement.filemanagerservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HttpResponse {
    private String message;
    private HttpStatus status;
}
