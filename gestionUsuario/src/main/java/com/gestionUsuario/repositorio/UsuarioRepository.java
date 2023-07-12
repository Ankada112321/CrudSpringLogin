package com.gestionUsuario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionUsuario.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
