package org.filesmanagement.authenticationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class AuthenticationValidation {

    private boolean isAuthenticated;
    private String username;



}
