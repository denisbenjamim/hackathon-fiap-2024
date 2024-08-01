package br.com.fiap.hackathon.spring.services;

import br.com.fiap.hackathon.spring.repository.AutenticacaoRepositoryJPA;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private final AutenticacaoRepositoryJPA autenticacaoRepositoryJPA;

    public AuthorizationService(AutenticacaoRepositoryJPA autenticacaoRepositoryJPA) {
        this.autenticacaoRepositoryJPA = autenticacaoRepositoryJPA;
    }

    @Cacheable("usersDetails")
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return autenticacaoRepositoryJPA.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com login: " + login));
    }
}
