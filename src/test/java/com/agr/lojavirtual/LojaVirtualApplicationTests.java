package com.agr.lojavirtual;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.agr.lojavirtual.controller.AcessoController;
import com.agr.lojavirtual.model.Acesso;
import com.agr.lojavirtual.repository.AcessoRepository;
import com.agr.lojavirtual.service.AcessoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;

@SpringBootTest(classes = LojaVirtualApplication.class)
public class LojaVirtualApplicationTests extends TestCase {

	@Autowired
	private AcessoService acessoService;

	@Autowired
	private AcessoRepository acessoRepository;

	@Autowired
	private AcessoController acessoController;

	@Autowired
	WebApplicationContext wac;

	public void testeRestCadastroAcesso() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
		MockMvc mockMvc = builder.build();

		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_COMPRADOR");

		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions resultActions = mockMvc
				.perform(MockMvcRequestBuilders.post("/salvarAcesso").content(objectMapper.writeValueAsString(acesso))
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));

		acesso = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), Acesso.class);

	}

	@Test
	public void testRestApiCadastroAcesso() throws JsonProcessingException, Exception {
		// Teste do end-point de salvar acesso
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();

		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_COMPRADOR");

		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions retornoApi = mockMvc
				.perform(MockMvcRequestBuilders.post("/salvarAcesso").content(objectMapper.writeValueAsString(acesso))
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));

		System.out.println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());

		Acesso objetoRetorno = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(),
				Acesso.class);

		assertEquals(true, objetoRetorno.getId() > 0);
		assertEquals(acesso.getDescricao(), objetoRetorno.getDescricao());

	}

	@Test
	public void testRestApiDeleteAcesso() throws JsonProcessingException, Exception {
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_ADMIN236");

		assertEquals(true, acesso.getId() == null);

		acesso = acessoRepository.save(acesso);

		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();

		acesso = acessoRepository.findById(acesso.getId()).orElse(null);
		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions retornoApi = mockMvc
				.perform(MockMvcRequestBuilders.delete("/deleteAcesso").content(objectMapper.writeValueAsString(acesso))
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));

		String objetoRetorno = retornoApi.andReturn().getResponse().getContentAsString();
		System.out.println("Status " + retornoApi.andReturn().getResponse().getStatus());

		acesso = acessoRepository.findById(acesso.getId()).orElse(null);

		assertEquals("Acesso removido", objetoRetorno);
		assertEquals(true, acesso == null);

	}

	@Test
	public void testRestApiDeleteAcessoPorId() throws JsonProcessingException, Exception {
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_ADMIN_POR_ID");

		assertEquals(true, acesso.getId() == null);

		acesso = acessoController.SalvarAcesso(acesso).getBody();

		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();

		acesso = acessoRepository.findById(acesso.getId()).orElse(null);
		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.delete("/deleteAcessoPorId/" + acesso.getId())
				.content(objectMapper.writeValueAsString(acesso.getId())).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));

		String objetoRetorno = retornoApi.andReturn().getResponse().getContentAsString();
		System.out.println("Retorno " + objetoRetorno);
		System.out.println("Status " + retornoApi.andReturn().getResponse().getStatus());

		acesso = acessoRepository.findById(acesso.getId()).orElse(null);

		assertEquals("Acesso removido por id", objetoRetorno);
		assertEquals(true, acesso == null);

	}

	@Test
	public void testRestApiObterAcessoPorId() throws JsonProcessingException, Exception {
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_ADMIN_Obter_POR_ID");

		assertEquals(true, acesso.getId() == null);

		acesso = acessoController.SalvarAcesso(acesso).getBody();

		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();

		acesso = acessoRepository.findById(acesso.getId()).orElse(null);
		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.get("/obterAcessoPorId/" + acesso.getId())
				.content(objectMapper.writeValueAsString(acesso.getId())).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON));

		String objetoRetorno = retornoApi.andReturn().getResponse().getContentAsString();
		System.out.println("Retorno " + objetoRetorno);
		System.out.println("Status " + retornoApi.andReturn().getResponse().getStatus());

		Acesso acessoRetorno = objectMapper.readValue(objetoRetorno, Acesso.class);

		acesso = acessoRepository.findById(acesso.getId()).orElse(null);

		assertEquals(acesso.getDescricao(), acessoRetorno.getDescricao());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

		acessoRepository.deleteById(acessoRetorno.getId());

	}

	@Test
	public void testRestApiObterAcessoPordesc() throws JsonProcessingException, Exception {
		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_ADMIN_Obter_POR_Desc");

		assertEquals(true, acesso.getId() == null);

		acesso = acessoController.SalvarAcesso(acesso).getBody();

		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();

		List<Acesso> a = acessoRepository.buscarAcessoDesc(acesso.getDescricao());

		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions retornoApi = mockMvc
				.perform(MockMvcRequestBuilders.get("/obterAcessoPordesc/" + acesso.getDescricao().trim().toUpperCase())
						.content(objectMapper.writeValueAsString(acesso)).accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON));

		String objetoRetorno = retornoApi.andReturn().getResponse().getContentAsString();
		System.out.println("Retorno " + objetoRetorno);
		System.out.println("Status " + retornoApi.andReturn().getResponse().getStatus());

		List<Acesso> acessoRetorno = objectMapper.readValue(objetoRetorno, new TypeReference<List<Acesso>>() {
		});

		acesso = acessoRepository.findById(acesso.getId()).orElse(null);

		System.out.println(acessoRetorno.size());

		assertEquals(1, acessoRetorno.size());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		
		acessoRetorno.forEach(ab -> {
			System.out.println(ab.getId());
			acessoRepository.deleteById(ab.getId());
		});

	}

	@Test
	public void testeCadastraAcesso() {

		Acesso acesso = new Acesso();
		acesso.setDescricao("ROLE_ADMIN2");

		assertEquals(true, acesso.getId() == null);

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

		assertEquals(1, acessos.size());

		acessoRepository.deleteById(acesso.getId());

	}

}
