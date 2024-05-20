package com.agr.lojavirtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agr.lojavirtual.ExceptionLojaVirtual;
import com.agr.lojavirtual.model.PessoaJuridica;
import com.agr.lojavirtual.service.PessoaJuridicaService;

//@CrossOrigin(origins = "https://www.agrsolucoes.com.br")
@Controller
@RestController()
public class PessoaController {
	
	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;
	
	@ResponseBody // Poder dar um retorno da API
	@PostMapping(value = "/salvarPJ")
	public ResponseEntity<PessoaJuridica> salvarPJ(@RequestBody PessoaJuridica pessoaJuridica)
	   throws ExceptionLojaVirtual {
		PessoaJuridica pessoaSalvo = pessoaJuridicaService.salvar(pessoaJuridica);
		if (pessoaSalvo == null) {
			throw new ExceptionLojaVirtual("O registro de Empresa já está cadastrado.");
		}
		return new ResponseEntity<PessoaJuridica>(pessoaSalvo, HttpStatus.OK);
	}
	
//	@ResponseBody   // Poder dar um retorno da API
//	@DeleteMapping(value = "/deletePessoa")
//	public ResponseEntity<String> deletePessoa(@RequestBody Pessoa pessoa) {
//		pessoaJuridicaService.delete(pessoa);
//		return new ResponseEntity<String>("Pessoa removido", HttpStatus.OK);
//	}
//	
//
//	// @Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
//	@ResponseBody   // Poder dar um retorno da API
//	@DeleteMapping(value = "/deletePessoaPorId/{id}")
//	public ResponseEntity<String> deletePessoaPorId(@PathVariable Long id) {
//		pessoaJuridicaService.delete(id);
//		return new ResponseEntity<String>("Pessoa removido por id", HttpStatus.OK);
//	}
//	
//	@ResponseBody   
//	@GetMapping(value = "/obterPessoaPorId/{id}")
//	public ResponseEntity<Pessoa> obterPessoaPorId(@PathVariable Long id)
//	   throws ExceptionLojaVirtual {
//		Pessoa pessoa = pessoaJuridicaService.obterPessoaPorId(id);
//		if (pessoa == null) {
//			throw new ExceptionLojaVirtual("Não encontrou pessoa com codigo:  " + id);
//		}
//		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
//	}
//	@ResponseBody   
//	@GetMapping(value = "/obterPessoaPorDesc/{desc}")
//	public ResponseEntity<List<Pessoa>> obterPessoaPorId(@PathVariable(name = "desc") String desc) {
//		List<Pessoa> pessoas = pessoaJuridicaService.obterPessoaPordesc(desc);
//		return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);
//	}
}
