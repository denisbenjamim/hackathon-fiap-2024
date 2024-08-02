package br.com.fiap.hackathon.spring.security;

import br.com.fiap.hackathon.spring.entity.AutenticacaoEntity;
import br.com.fiap.hackathon.spring.repository.AutenticacaoRepositoryJPA;
import br.com.fiap.hackathon.spring.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final AutenticacaoRepositoryJPA userRepository;

    public SecurityFilter(TokenService tokenService, AutenticacaoRepositoryJPA userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        validarToken(token);
        filterChain.doFilter(request, response);
    }

    private void validarToken(String token) {
        if (token != null) {
            var login = tokenService.validateToken(token);
            
           autenticarByLogin(login);
        }
    }

    private void autenticarByLogin(String login){
        if (login != null) {
            AutenticacaoEntity user = userRepository.findByLogin(login).orElse(null);
            
            setAuthentication(user);
        }
    }

    private void setAuthentication(AutenticacaoEntity user) {
        if (user != null) {
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
