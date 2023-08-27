package org.filemanagement.notificationservice.config.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Notification Service ",
                        email = "contact@filemanagementapp.com",
                        url = "http://filemanagementapp.org"
                ),
                description = "Authentication service for sending PUSH NOTIFICATION to users, developped using FCM",
                title =  "OpenApi Spec - Authentication server",
                version = "1.0"

        )
)
public class OpenApiConfig {
}
