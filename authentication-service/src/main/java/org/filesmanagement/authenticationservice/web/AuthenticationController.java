package org.filesmanagement.authenticationservice.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.filesmanagement.authenticationservice.dto.AuthenticationDTO;
import org.filesmanagement.authenticationservice.model.AuthenticationValidation;
import org.filesmanagement.authenticationservice.security.SecurityConstants;
import org.filesmanagement.authenticationservice.security.services.AuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication Service")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response){

        authenticationService.refreshToken();

        return ResponseEntity.ok("refresh token");
    }

    @Operation(
            description = "Validate JWT Token",
            responses = {
                    @ApiResponse(
                            description = "Valid Token",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "FAILED in case of expired token or bad signature",
                            responseCode = "401"
                    ),


            }
    )
    @GetMapping(value = "/validate-token")
    public ResponseEntity<AuthenticationValidation> validateToken(HttpServletRequest request){

        String username = (String) request.getAttribute("username");
      return  ResponseEntity.ok(AuthenticationValidation.builder().username(username).isAuthenticated(true).build());

    }



}
