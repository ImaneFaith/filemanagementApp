package org.filesmanagement.authenticationservice.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.filesmanagement.authenticationservice.dto.AuthenticationDTO;
import org.filesmanagement.authenticationservice.model.AuthenticationSuccessfulResponse;
import org.filesmanagement.authenticationservice.security.services.utils.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.filesmanagement.authenticationservice.security.SecurityConstants;

import java.io.IOException;

import java.util.*;

@RequiredArgsConstructor
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        try {
            log.info("Attempt to authenticate >>>>>>>>>>>>>");
            AuthenticationDTO authModel = mapper.readValue(request.getInputStream(), AuthenticationDTO.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(authModel.getUsername(), authModel.getPassword());
            return authenticationManager.authenticate(authentication);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        Map<String,Object> claims = Map.of("authorities",authResult.getAuthorities());
        String token = jwtService.generateToken(claims,authResult.getName());

        response.addHeader(SecurityConstants.HEADER, String.format("Bearer %s", token));

        AuthenticationSuccessfulResponse authSuccResp = AuthenticationSuccessfulResponse.builder()
                                                        .token(token)
                                                        .build();

        response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().write(mapper.writeValueAsBytes(authSuccResp));

    }
}
