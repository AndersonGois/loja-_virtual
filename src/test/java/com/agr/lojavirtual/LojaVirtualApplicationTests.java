package com.agr.lojavirtual;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.agr.lojavirtual.controller.AcessoController;
import com.agr.lojavirtual.model.Acesso;
import com.agr.lojavirtual.repository.AcessoRepository;
import com.agr.lojavirtual.service.AcessoService;

import junit.framework.Assert;
import junit.framework.TestCase;

@SpringBootTest(classes = LojaVirtualApplication.class)
public class LojaVirtualApplicationTests extends TestCase {

	@Autowired
	private AcessoService acessoService;

	@Autowired
	private AcessoRepository acessoRepository;

	@Autowired
	private AcessoController acessoController;

	@Test
	public void testeCadastraAcesso() {

		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_ADMIN2");

		
		assertEquals(true, acesso.getId() == null );
		
		
		acesso = acessoController.SalvarAcesso(acesso).getBody();
		
		assertEquals(true, acesso.getId() > 0);
		assertEquals("ROLE_ADMIN2", acesso.getDescricao());
		
		
		Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();
		
		assertEquals(acesso.getId(), acesso2.getId());
		
		acessoRepository.deleteById(acesso.getId());
		
		acessoRepository.flush();
		
		Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null);

		assertEquals(null, acesso3);

		 acesso = new Acesso();
		acesso.setDescricao("ROLE_ALUNO");
		
		acesso = acessoController.SalvarAcesso(acesso).getBody();
		
		List<Acesso> acessos = acessoRepository.buscarAcessoDesc("ALUNO".trim().toUpperCase());
		
		assertEquals(1,acessos.size());
		
		acessoRepository.deleteById(acesso.getId());
		
		
		
	}

}
