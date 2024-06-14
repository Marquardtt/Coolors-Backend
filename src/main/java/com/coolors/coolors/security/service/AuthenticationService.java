package com.coolors.coolors.security.service;

import com.coolors.coolors.entity.Usuario;
import com.coolors.coolors.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthenticationService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuarioSecurity = usuarioRepository.findByEmail(username);
        if (usuarioSecurity.isPresent()) {
            usuarioSecurity.get().setEnabled(true);
            return usuarioSecurity.get();
        }
        throw new UsernameNotFoundException("Dados invalidos");
    }
}