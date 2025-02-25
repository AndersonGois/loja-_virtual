package com.agr.lojavirtual.controller;

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
	@PostMapping(value = "/salvarAcesso")
	public ResponseEntity<?> salvarAcesso(@RequestBody Acesso acesso)
	   throws ExceptionLojaVirtual {
		try {
			Acesso acessoSalvo = acessoService.salvar(acesso);
			
			return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
			
		} catch (ExceptionLojaVirtual e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
//	
//	@ResponseBody
//	@PostMapping(value = "/salvar")
//	public ResponseEntity<?> salvarAcesso(@RequestBody Acesso acesso)
//	   throws ExceptionLojaVirtual {
//		try {
//			Acesso acessosalvo = acessoService.salvar2(acesso);
//			return new ResponseEntity<>(acessosalvo, HttpStatus.OK);
//			
//		} catch (ExceptionLojaVirtual e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}
	
	
	@ResponseBody   // Poder dar um retorno da API
	@DeleteMapping(value =  "/deleteAcesso")
	public ResponseEntity<String>  deleteAcesso(@RequestBody Acesso acesso) {
		 acessoService.delete(acesso);
		return new ResponseEntity<String>("Acesso removido",HttpStatus.OK);
	}
	

	// @CrossOrigin(origins = "https://www.site.com")
	// @Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
	@ResponseBody   // Poder dar um retorno da API
	@DeleteMapping(value =  "/deleteAcessoPorId/{id}")
	public ResponseEntity<String>  deleteAcessoPorId(@PathVariable Long id) {
		acessoService.delete(id);
		return new ResponseEntity<String>("Acesso removido por id",HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "/obterAcessoPorId/{id}")
	public ResponseEntity<Acesso> obterAcessoPorId(@PathVariable Long id)
	   throws ExceptionLojaVirtual {
		Acesso acesso = acessoService.obterAcessoPorId(id);
		if (acesso == null) {
			throw new ExceptionLojaVirtual("Não encontrou acesso com codigo:  " + id);
		}
		return new ResponseEntity<Acesso>(acesso, HttpStatus.OK);
	}
	
//	@ResponseBody //TODO: verificar esse codigo
//	@GetMapping(value = "/obterAcessoById/{id}")
//	public ResponseEntity<Acesso> obterAcessoById(@PathVariable Long id)
//	   throws ExceptionLojaVirtual {
//		Acesso acesso = acessoService.obterAcessoByid2(id);
//		if (acesso == null) {
//			throw new ExceptionLojaVirtual("Não encontrou acesso com codigo " + id);
//		}
//		return new ResponseEntity<Acesso>(acesso,HttpStatus.OK);
//	}
	
//	@ResponseBody   
//	@GetMapping(value = "/obterAcessoPorDesc/{desc}")
//	public ResponseEntity<List<Acesso>>  obterAcessoPorId(@PathVariable(name = "desc") String desc) {
//		List<Acesso> acessos = acessoService.obterAcessoPordesc(desc);
//		return new ResponseEntity<List<Acesso>>(acessos,HttpStatus.OK);
//	}
}
