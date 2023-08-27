package org.filemanagement.filemanagerservice.security.communication.models;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationValidation {
    private String username;
    private boolean authenticated;
}
