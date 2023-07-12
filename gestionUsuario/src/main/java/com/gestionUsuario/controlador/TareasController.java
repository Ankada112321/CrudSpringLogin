package com.gestionUsuario.controlador;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gestionUsuario.modelo.Usuario;
import com.gestionUsuario.modelo.Tareas;
import com.gestionUsuario.repositorio.TareasRepository;
import com.gestionUsuario.repositorio.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tareas")
public class TareasController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TareasRepository tareasRepository;
	
	@GetMapping
	public ResponseEntity<Page<Tareas>> listarTareas(Pageable pageable){
		return ResponseEntity.ok(tareasRepository.findAll(pageable));
	}
	
	@PostMapping
	public ResponseEntity<Tareas> guardarLibro(@Valid @RequestBody Tareas tareas){
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(tareas.getUsuarios().getId());
		
		if(!usuarioOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		tareas.setUsuarios(usuarioOptional.get());
		Tareas tareaGuardada = tareasRepository.save(tareas);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(tareaGuardada.getId()).toUri();
		
		return ResponseEntity.created(ubicacion).body(tareaGuardada);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tareas> actualizarTarea(@Valid @RequestBody Tareas tareas,@PathVariable Integer id){
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(tareas.getUsuarios().getId());
		
		if(!usuarioOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		Optional<Tareas> tareaOptional = tareasRepository.findById(id);
		if(!tareaOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		tareas.setUsuarios(usuarioOptional.get());
		tareas.setId(tareaOptional.get().getId());
		tareasRepository.save(tareas);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Tareas> eliminartarea(@PathVariable Integer id){
		Optional<Tareas> tareaOptional = tareasRepository.findById(id);
		
		if(!tareaOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		tareasRepository.delete(tareaOptional.get());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tareas> obtenerTareaPorId(@PathVariable Integer id){
		Optional<Tareas> tareaOptional = tareasRepository.findById(id);
		
		if(!tareaOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.ok(tareaOptional.get());
	}
}
