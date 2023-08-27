package org.filesmanagement.authenticationservice.security.config;


import org.filesmanagement.authenticationservice.security.exceptions.ExceptionHandlerFilter;
import org.filesmanagement.authenticationservice.security.filters.JWTAuthenticationFilter;
import org.filesmanagement.authenticationservice.security.filters.JWTVerifierFilter;
import org.filesmanagement.authenticationservice.security.services.JpaUserDetailsService;
import org.filesmanagement.authenticationservice.security.services.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig {

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;
    @Autowired
    private AuthenticationConfiguration authConfig;

    @Autowired
    private JwtService jwtService;





    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.csrf(csrfToken -> csrfToken.disable())
                .sessionManagement(s ->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(new JWTAuthenticationFilter(authenticationManager(),jwtService))
                .addFilterAfter(new JWTVerifierFilter(jwtService), JWTAuthenticationFilter.class)
                .addFilterBefore(new ExceptionHandlerFilter(),JWTVerifierFilter.class)
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(authz -> {
                       authz.requestMatchers("/login").permitAll()
                               .requestMatchers(
                                       "/v3/api-docs",
                                       "/v3/api-docs/**",
                                       "/swagger-ui/**"
                               ).permitAll()
                               .requestMatchers("/validate-token").permitAll()
                    .anyRequest().authenticated();
                })
                .build();
    }





    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        authProvider.setUserDetailsService(jpaUserDetailsService);

        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
       return authConfig.getAuthenticationManager();
    }



    @Bean
    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
