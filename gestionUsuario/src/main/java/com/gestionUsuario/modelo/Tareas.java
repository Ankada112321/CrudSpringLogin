package com.gestionUsuario.modelo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tareas")
public class Tareas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String titulo;
	@Column
	private String descripcion;
	@Column
	private Date fechaVencimiento;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "usuario_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Usuario usuarios;
	
	public Tareas() {
		super();
	}
	
	
	public Tareas(int id, String titulo, String descripcion, Date fechaVencimiento, Usuario usuarios) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaVencimiento = fechaVencimiento;
		this.usuarios = usuarios;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Usuario getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Usuario usuarios) {
		this.usuarios = usuarios;
	}


	@Override
	public String toString() {
		return "Tareas [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", fechaVencimiento="
				+ fechaVencimiento + ", usuarios=" + usuarios + "]";
	}
	
	
}
