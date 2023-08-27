package org.filesmanagement.authenticationservice.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationSuccessfulResponse {

    private String token;
}
