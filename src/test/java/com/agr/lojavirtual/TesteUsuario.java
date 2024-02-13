package com.agr.lojavirtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.agr.lojavirtual.model.PessoaFisica;
import com.agr.lojavirtual.model.PessoaJuridica;
import com.agr.lojavirtual.repository.AcessoRepository;
import com.agr.lojavirtual.repository.PessoaRepository;
import com.agr.lojavirtual.repository.UsuarioRepository;

import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualApplication.class)
public class TesteUsuario extends TestCase {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private AcessoRepository acessoRepository;
	
	@Test
	public void testCadUser() {
		
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setCnpj("07328241764");
		pessoaJuridica.setNome("Rejane24");
		pessoaJuridica.setEmail("rejanefridarj@gmail.com");
		pessoaJuridica.setTelefone("21998534343");
		pessoaJuridica.setInscEstadual("21998534343");
		pessoaJuridica.setInscMunicipal("21998534343");
		pessoaJuridica.setNomeFantasia("Agr Soluçoes25");
		pessoaJuridica.setRazaoSocial("Agr Soluçoes25");
		// pessoaJuridica.setEmpresa(pessoaJuridica);
		
		pessoaJuridica = pessoaRepository.save(pessoaJuridica);

		PessoaFisica pessoaFisica = new PessoaFisica();
		pessoaFisica.setCpf("07328241764");
		pessoaFisica.setNome("Rejane");
		pessoaFisica.setEmail("rejanefridarj@gmail.com");
		pessoaFisica.setTelefone("21998534343");
		pessoaFisica.setEmpresa(pessoaJuridica);
		
		pessoaFisica = pessoaRepository.save(pessoaFisica);
//		
//		System.out.println(pessoaFisica);
//		
//		Usuario usuario = new Usuario();
//		usuario.setLogin("rejane");
//		usuario.setPessoa(pessoaFisica);
//		usuario.setSenha(new BCryptPasswordEncoder().encode("agr"));
//		usuario.setDataAtualSenha(Calendar.getInstance().getTime());
//		
//		usuario = usuarioRepository.save(usuario);
//		
//		Acesso acesso = acessoRepository.findAll().get(0);
//		
//		String constraint = usuarioRepository.consultaContraintRole();
//		
////		if (constraint != null) {
//			//jdbcT
//		}
		
	}
	
}
