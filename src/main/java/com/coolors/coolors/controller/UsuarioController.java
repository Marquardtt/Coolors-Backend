package com.coolors.coolors.controller;

import com.coolors.coolors.entity.Usuario;
import com.coolors.coolors.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {
    private UsuarioService service;

    @PostMapping("/create")
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario){
        return service.criarUsuario(usuario);
    }
}
