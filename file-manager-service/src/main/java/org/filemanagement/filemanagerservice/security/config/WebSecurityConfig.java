package org.filemanagement.filemanagerservice.security.config;

import org.filemanagement.filemanagerservice.security.communication.config.AuthCommunicationConfig;
import org.filemanagement.filemanagerservice.security.communication.config.CommunicationConfig;
import org.filemanagement.filemanagerservice.security.communication.services.AuthenticationCommunicationService;
import org.filemanagement.filemanagerservice.security.filters.ExceptionHandlerFilter;
import org.filemanagement.filemanagerservice.security.filters.JWTVerifierFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig {

    @Autowired
    private AuthenticationCommunicationService authService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrfToken -> csrfToken.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(
                                    "/v3/api-docs",
                                    "/v3/api-docs/**",
                                    "/swagger-ui/**"
                            ).permitAll()
                            .anyRequest().authenticated();
                })

                .addFilterBefore(new JWTVerifierFilter(ctx(),authService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new ExceptionHandlerFilter(), JWTVerifierFilter.class)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager();
    }

    @Bean
    public SecurityContext ctx() {
        return new SecurityContextImpl();
    }
}
