package com.gestionUsuario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionUsuario.modelo.Tareas;



public interface TareasRepository extends JpaRepository<Tareas, Integer> {

}
