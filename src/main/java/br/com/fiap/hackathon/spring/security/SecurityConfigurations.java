package br.com.fiap.hackathon.spring.security;

import br.com.fiap.hackathon.spring.services.AuthorizationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;
    private final AuthorizationService authorizationService;
    private final UnauthorizedEntryPoint unauthorizedEntryPoint;

    public SecurityConfigurations(SecurityFilter securityFilter, AuthorizationService authorizationService, UnauthorizedEntryPoint unauthorizedEntryPoint) {
        this.securityFilter = securityFilter;
        this.authorizationService = authorizationService;
        this.unauthorizedEntryPoint = unauthorizedEntryPoint;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(uriLiberadas()).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedEntryPoint))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .userDetailsService(authorizationService)
                .build();
    }

    private String[] uriLiberadas() {
        return new String[]{
                "/h2-console/**",
                "/api-docs/**",
                "/swagger-resources/**",
                "/configuration/**",
                "/webjars/**",
                "/v3/**",
                "/swagger-ui/**",
                "v3/api-docs/**",
                "/api/autenticacao/**"
        };
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
