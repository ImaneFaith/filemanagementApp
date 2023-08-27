package org.filesmanagement.authenticationservice.security.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.filesmanagement.authenticationservice.model.AuthenticationValidation;
import org.filesmanagement.authenticationservice.security.exceptions.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | SignatureException | MalformedJwtException ex) {
            log.info(ex.getMessage());
            AuthenticationValidation authValid = AuthenticationValidation.builder().isAuthenticated(false).build();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
           out.write(mapper.writeValueAsBytes(authValid));
        }
    }
}
