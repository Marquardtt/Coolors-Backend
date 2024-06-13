package com.coolors.coolors.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 70, nullable = false)
    private String nome;
    @Column(nullable = false)
    private String senha;
    @Column(unique = true, nullable = false)
    private String email;
}
