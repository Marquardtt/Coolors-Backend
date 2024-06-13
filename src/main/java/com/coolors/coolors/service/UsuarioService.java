package com.coolors.coolors.service;

import com.coolors.coolors.entity.Usuario;
import com.coolors.coolors.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {
    private UsuarioRepository repository;

    public ResponseEntity<?> criarUsuario(Usuario usuario){
        try{
            repository.save(usuario);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}