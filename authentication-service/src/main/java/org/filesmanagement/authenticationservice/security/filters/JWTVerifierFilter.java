package org.filesmanagement.authenticationservice.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.filesmanagement.authenticationservice.security.SecurityConstants;

import java.util.List;

import org.filesmanagement.authenticationservice.security.services.utils.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JWTVerifierFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws AuthenticationException, ServletException, IOException {

        String bearerToken = request.getHeader(SecurityConstants.HEADER);
        if (bearerToken == null || !bearerToken.startsWith(SecurityConstants.PREFIX)) {
            log.info("bearerr {}", bearerToken);
            filterChain.doFilter(request, response);
            return;
        }

        String authToken = bearerToken.replace(SecurityConstants.PREFIX, "");

        String username = jwtService.extractUsername(authToken);
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) jwtService.extractAuthorities(authToken);
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        request.setAttribute("username", username);
        filterChain.doFilter(request, response);


    }
}
