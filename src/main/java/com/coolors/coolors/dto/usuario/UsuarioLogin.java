package com.coolors.coolors.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UsuarioLogin {
    private String username;
    private String password;
}
