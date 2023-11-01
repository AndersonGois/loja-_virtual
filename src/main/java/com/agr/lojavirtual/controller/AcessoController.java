package com.agr.lojavirtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agr.lojavirtual.model.Acesso;
import com.agr.lojavirtual.service.AcessoService;

@Controller
@RestController()
public class AcessoController {

	@Autowired
	private AcessoService acessoService;
	
	@ResponseBody   // Poder dar um retorno da API
	@PostMapping(value =  "/salvarAcesso")
	public ResponseEntity<Acesso>  SalvarAcesso(@RequestBody Acesso acesso) {
		Acesso acessoSalvo = acessoService.save(acesso);
		return new ResponseEntity<Acesso>(acessoSalvo,HttpStatus.OK);
	}
	@ResponseBody   // Poder dar um retorno da API
	@DeleteMapping(value =  "/deleteAcesso")
	public ResponseEntity<String>  deleteAcesso(@RequestBody Acesso acesso) {
		 acessoService.delete(acesso);
		return new ResponseEntity<String>("Acesso removido",HttpStatus.OK);
	}
}
