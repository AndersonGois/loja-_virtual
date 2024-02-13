package com.agr.lojavirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agr.lojavirtual.ExceptionLojaVirtual;
import com.agr.lojavirtual.model.Acesso;
import com.agr.lojavirtual.service.AcessoService;

//@CrossOrigin(origins = "https://www.agrsolucoes.com.br")
@Controller
@RestController()
public class AcessoController {

	@Autowired
	private AcessoService acessoService;
	
	@ResponseBody // Poder dar um retorno da API
	@PostMapping(value =  "/salvarAcesso")
	public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso)
	   throws ExceptionLojaVirtual {
		Acesso acessoSalvo = acessoService.salvar(acesso);
		if (acessoSalvo == null) {
			throw new ExceptionLojaVirtual("O registro de acesso já está cadastrado.");
		}
		return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
	}
	
	
	@ResponseBody   // Poder dar um retorno da API
	@DeleteMapping(value =  "/deleteAcesso")
	public ResponseEntity<String>  deleteAcesso(@RequestBody Acesso acesso) {
		 acessoService.delete(acesso);
		return new ResponseEntity<String>("Acesso removido",HttpStatus.OK);
	}
	

	// @Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	@ResponseBody   // Poder dar um retorno da API
	@DeleteMapping(value =  "/deleteAcessoPorId/{id}")
	public ResponseEntity<String>  deleteAcessoPorId(@PathVariable Long id) {
		acessoService.delete(id);
		return new ResponseEntity<String>("Acesso removido por id",HttpStatus.OK);
	}
	
	@ResponseBody   
	@GetMapping(value =  "/obterAcessoPorId/{id}")
	public ResponseEntity<Acesso> obterAcessoPorId(@PathVariable Long id)
	   throws ExceptionLojaVirtual {
	Acesso acesso = acessoService.obterAcessoPorId(id);
		if (acesso == null) {
			throw new ExceptionLojaVirtual("Não encontrou acesso com codigo:  " + id);
		}
		return new ResponseEntity<Acesso>(acesso,HttpStatus.OK);
	}
	@ResponseBody   
	@GetMapping(value = "/obterAcessoPorDesc/{desc}")
	public ResponseEntity<List<Acesso>>  obterAcessoPorId(@PathVariable(name = "desc") String desc) {
		List<Acesso> acessos = acessoService.obterAcessoPordesc(desc);
		return new ResponseEntity<List<Acesso>>(acessos,HttpStatus.OK);
	}
}
