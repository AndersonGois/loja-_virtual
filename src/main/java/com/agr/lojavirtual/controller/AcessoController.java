package com.agr.lojavirtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.agr.lojavirtual.model.Acesso;
import com.agr.lojavirtual.service.AcessoService;

@Controller
public class AcessoController {

	@Autowired
	private AcessoService acessoService;
	
	@PostMapping("/salvarAcesso")
	public Acesso SalvarAcesso(Acesso acesso) {
		return acessoService.save(acesso);
	}
}
