package com.gestionUsuario.modelo;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String nombre;
	@Column
	private String perfil;
	@Column
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Set<Tareas> tareas = new HashSet<>();
	
	
	
	public Usuario() {
		super();
	}
	public Usuario(int id, String nombre, String perfil, Set<Tareas> tareas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.perfil = perfil;
		this.tareas = tareas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public Set<Tareas> getTareas() {
		return tareas;
	}
	public void setTareas(Set<Tareas> tareas) {
		this.tareas = tareas;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", perfil=" + perfil + ", tareas=" + tareas + "]";
	}
	
	

}
