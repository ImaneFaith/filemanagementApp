package org.filemanagement.filemanagerservice.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.filemanagement.filemanagerservice.security.SecurityConstants;
import org.filemanagement.filemanagerservice.security.communication.config.AuthCommunicationConfig;
import org.filemanagement.filemanagerservice.security.communication.models.AuthenticationValidation;
import org.filemanagement.filemanagerservice.security.communication.services.AuthenticationCommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class JWTVerifierFilter extends OncePerRequestFilter {

    @Autowired
    private SecurityContext ctx;

    @Autowired
    private  AuthenticationCommunicationService auth;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        log.info("verifier JWT Filter :  File Manager Service");

        String bearerToken = request.getHeader(SecurityConstants.HEADER);
        if (bearerToken == null || !bearerToken.startsWith(SecurityConstants.PREFIX)) {
            log.info("token {}", bearerToken);
            filterChain.doFilter(request, response);
            return;
        }

            AuthenticationValidation authValid = this.auth.validateToken(bearerToken).block();

            assert authValid != null;

            if(authValid.isAuthenticated()){
                Authentication authentication = new UsernamePasswordAuthenticationToken(authValid.getUsername(), null, null);
                ctx.setAuthentication(authentication);

                SecurityContextHolder.getContext().setAuthentication(authentication);

                //request.setAttribute("username", authValid.getUsername());

          }




        filterChain.doFilter(request, response);
    }
}
