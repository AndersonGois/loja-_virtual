package com.agr.lojavirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agr.lojavirtual.ExceptionLojaVirtual;
import com.agr.lojavirtual.model.UsuarioTeste;
import com.agr.lojavirtual.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RestController
public class UsuarioTesteController {
	
	@Autowired
	private UsuarioService userService;
	
	@ResponseBody
	@PostMapping(value = "/salvarUsuarioTeste")
	public ResponseEntity<?> salvarUsuario(@RequestBody UsuarioTeste user)
	   throws ExceptionLojaVirtual {
		try {
			UsuarioTeste userSalvo = userService.salvar(user);
			return ResponseEntity.status(HttpStatus.OK).body(userSalvo);
			
		} catch (ExceptionLojaVirtual e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseBody
	@PutMapping(value = "/alterarUsuarioTeste")
	public ResponseEntity<?> alterarUsuario(@RequestBody UsuarioTeste user)
	   throws ExceptionLojaVirtual {
		try {
			UsuarioTeste userSalvo = userService.alterar(user);
			return ResponseEntity.status(HttpStatus.OK).body(userSalvo);
			
		} catch (ExceptionLojaVirtual e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseBody
	@DeleteMapping(value = "/deleteUsuarioTeste/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable Long id) throws ExceptionLojaVirtual {
		try {
			userService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("deletado com sucesso");
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseBody
	@GetMapping(value = "/todosUsuariosTeste")
	public ResponseEntity<?> todosUsuariosTeste()
	   throws ExceptionLojaVirtual, JsonProcessingException {
		List<UsuarioTeste> todos = userService.todosUsuarios();
		
		System.out.println("TODO: " + new ObjectMapper().writeValueAsString(todos));
		return ResponseEntity.status(HttpStatus.OK).body(todos);
	}
	
	@ResponseBody
	@GetMapping(value = "/usuarioById")
	public ResponseEntity<?> usuarioById(@RequestParam(value = "id") Long id)
	   throws ExceptionLojaVirtual, JsonProcessingException {
		UsuarioTeste usuario = userService.usuarioById(id);
		
		System.out.println("TODO: " + new ObjectMapper().writeValueAsString(usuario));
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}
	
	@ResponseBody
	@GetMapping(value = "/usuarioById/{id}")
	public ResponseEntity<?> usuarioByIdPath(@PathVariable(value = "id") Long id)
	   throws ExceptionLojaVirtual, JsonProcessingException {
		UsuarioTeste usuario = userService.usuarioById(id);
		
		System.out.println("TODO: " + new ObjectMapper().writeValueAsString(usuario));
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}
	
	@ResponseBody
	@GetMapping(value = "/usuarioByIdb")
	public ResponseEntity<?> usuarioByIdBody(@RequestBody UsuarioTeste user)
	   throws ExceptionLojaVirtual, JsonProcessingException {
		UsuarioTeste usuario = userService.usuarioById(user.getId());
		
		System.out.println("TODO: " + new ObjectMapper().writeValueAsString(usuario));
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}
	
	@ResponseBody
	@GetMapping(value = "/paginado")
	public ResponseEntity<Page<UsuarioTeste>> getUsuariosPaginados(
	   @RequestParam(value = "page") int page, @RequestParam(value = "size") int size)
	   throws ExceptionLojaVirtual, JsonProcessingException {
		Page<UsuarioTeste> usuarios = userService.findAllUsuarioPaginado(page, size);
		
		if (usuarios.hasContent()) {
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
}
