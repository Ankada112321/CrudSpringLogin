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
import com.gestionUsuario.repositorio.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioContoller {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public ResponseEntity<Page<Usuario>> listarUsuarios(Pageable pageable){
		return ResponseEntity.ok(usuarioRepository.findAll(pageable));
	}
	
	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@Valid @RequestBody Usuario usuario){
		Usuario usuarioGuardado = usuarioRepository.save(usuario);
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(usuarioGuardado.getId()).toUri();
		return ResponseEntity.created(ubicacion).body(usuarioGuardado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id,@Valid @RequestBody Usuario usuario){
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		
		if(!usuarioOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		usuario.setId(usuarioOptional.get().getId());
		usuarioRepository.save(usuario);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Integer id){
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		
		if(!usuarioOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		usuarioRepository.delete(usuarioOptional.get());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Integer id){
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		
		if(!usuarioOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.ok(usuarioOptional.get());
	}
	
	
	
	
	
}
