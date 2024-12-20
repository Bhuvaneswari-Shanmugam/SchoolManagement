package com.school.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.school.management.service.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true)
public class AuthenticationConfiguration {

	private final UserService userService;
    private final SecurityFilter securityFilter;

    
    public AuthenticationConfiguration(UserService userService, SecurityFilter securityFilter) {
        this.userService = userService;
        this.securityFilter = securityFilter;}

	@Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf ->csrf.disable())
            .authorizeHttpRequests(request -> request              
                    
                    .requestMatchers("/api/v1/auth/**").permitAll()
                    .requestMatchers("/admin/**").hasAuthority("ADMIN")
                    .requestMatchers("/student/**").hasAnyAuthority("STUDENT", "TEACHER","ADMIN")
                    .requestMatchers("/teacher/**").hasAnyAuthority("TEACHER","ADMIN")
                    .requestMatchers("/question/**").hasAuthority("TEACHER")
                    .requestMatchers("/answer/**").hasAnyAuthority("TEACHER","STUDENT")
                    .requestMatchers("/subject/**").hasAnyAuthority("ADMIN", "TEACHER")
                    .anyRequest().authenticated())     
            
            .sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();    
    }
    @Bean
     AuthenticationManager authenticationManager(org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    
    
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
