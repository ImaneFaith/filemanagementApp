package org.filesmanagement.authenticationservice;


import org.filesmanagement.authenticationservice.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.filesmanagement.authenticationservice.entities.User;



@SpringBootApplication
public class AuthenticationServiceApplication {

	@Autowired
	private UserService userService;


	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner initiliazeDB(){
		return (args) -> {
			User user = User.builder().username("person-123").password("$2a$12$bUHHr93kcdBO5q4hF4fr9ujA3.UYHYn/91P7R691XJOYHE9BrVnSy").build();
			userService.saveUser(user);
		};
	}

}
