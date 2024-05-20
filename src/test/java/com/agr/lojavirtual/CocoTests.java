package com.agr.lojavirtual;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.agr.lojavirtual.model.Coco;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Profile("dev")
@SpringBootTest(classes = LojaVirtualApplication.class)
public class CocoTests {
	
	@Autowired
	WebApplicationContext wac;
	private Coco coco;
	
	@BeforeEach
	public void init() throws Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
		MockMvc mockMvc = builder.build();
		// Cria um novo objeto Coco
		Coco novoCoco = new Coco();
		novoCoco.setCheiro("Bom" + Calendar.getInstance().getTimeInMillis());
		novoCoco.setDono("Anderson" + Calendar.getInstance().getTimeInMillis());
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		// Salva o novo objeto Coco
		ResultActions resultadoSalvar = mockMvc.perform(MockMvcRequestBuilders.post("/salvarCoco")
		   .content(objectMapper.writeValueAsString(novoCoco)).accept(MediaType.APPLICATION_JSON)
		   .contentType(MediaType.APPLICATION_JSON));
		
		// Desserializa o retorno da API para um objeto Coco
		coco = objectMapper.readValue(resultadoSalvar.andReturn().getResponse().getContentAsString(),
		   Coco.class);
	}
	
	@AfterEach
	public void aftercada() throws Exception {
		System.out.println("AfterEach " + coco.getId());
		// ID do objeto Coco que você deseja deletar
		Long cocoId = coco.getId();
		System.out.println("TODO: _id " + coco.getId());
		// Cria um MockMvc a partir do contexto da aplicação web
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
		MockMvc mockMvc = builder.build();
		
		// Define um ObjectMapper para serializar e desserializar
		// objetos em JSON
		ObjectMapper mapper = new ObjectMapper();
		
		// Realiza uma requisição DELETE para a rota
		// /deleteCoco/{id}
		ResultActions resultadoAPI = mockMvc
		   .perform(MockMvcRequestBuilders.delete("/deleteCoco/{id}", cocoId) // Substitui {id} pelo
		                                                                      // ID real do objeto
		                                                                      // Coco
		      .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		
		// Imprime o retorno da API
		System.out
		   .println("Retorno da API: " + resultadoAPI.andReturn().getResponse().getContentAsString());
	}
	
	@Test
	@Order(1)
	public void testeRestCadastroCoco() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
		MockMvc mockMvc = builder.build();
		
		Coco coco = new Coco();
		coco.setCheiro("Bom" + Calendar.getInstance().getTimeInMillis());
		coco.setDono("Anderson" + Calendar.getInstance().getTimeInMillis());
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.post("/salvarCoco")
		   .content(objectMapper.writeValueAsString(coco)).accept(MediaType.APPLICATION_JSON)
		   .contentType(MediaType.APPLICATION_JSON));
		
		System.out
		   .println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
		
		Coco objetoRetorno = objectMapper
		   .readValue(retornoApi.andReturn().getResponse().getContentAsString(), Coco.class);
		
		System.out.println("salvar" + coco.getId());
		assertEquals(true, objetoRetorno.getId() > 0);
		assertEquals(coco.getCheiro(), objetoRetorno.getCheiro());
		
	}
	
	@Test
	@Order(2)
	public void testeTodosCocos() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
		MockMvc mockMvc = builder.build();

		
		ObjectMapper mapper = new ObjectMapper();
		ResultActions retornoApi = mockMvc
		   .perform(MockMvcRequestBuilders.get("/todosCocos")
		      .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		
		System.out
		   .println("TODO: Retorno API" + retornoApi.andReturn().getResponse().getContentAsString());
		
		List<Coco> cocos = mapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(),
		   new TypeReference<List<Coco>>() {
		   });
		
		assertEquals(true, cocos.size() > 0);
		
	}
	
	@Test
	@Order(3)
	public void testAlterarCoco() throws JsonProcessingException, Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
		MockMvc mockMvc = builder.build();
		System.out.println("testAlterarCoco " + coco.getId());
		Coco co = new Coco();
		co.setId(coco.getId());
		co.setCheiro("brabo");
		co.setDono("brabo");
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions resultadoAPI = mockMvc
		   .perform(MockMvcRequestBuilders.put("/alterarCoco").content(mapper.writeValueAsString(co))
		      .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		
		System.out.println(
		   "TODO: retorno da api" + resultadoAPI.andReturn().getResponse().getContentAsString());
		Coco retotnoApi = mapper
		   .readValue(resultadoAPI.andReturn().getResponse().getContentAsString(), Coco.class);
		
		assertEquals(true, retotnoApi.getCheiro().equals("brabo"));
		assertEquals(co.getCheiro(), retotnoApi.getCheiro());
		
	}
	
	

	@Test
	@Order(4)
	public void testDeleteCoco() throws Exception {
		System.out.println("testDeleteCoco " + coco.getId());
		// ID do objeto Coco que você deseja deletar
		Long cocoId = coco.getId();
		System.out.println("TODO: _id " + coco.getId());
		// Cria um MockMvc a partir do contexto da aplicação web
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
		MockMvc mockMvc = builder.build();
		
		// Define um ObjectMapper para serializar e desserializar
		// objetos em JSON
		ObjectMapper mapper = new ObjectMapper();
		
		// Realiza uma requisição DELETE para a rota
		// /deleteCoco/{id}
		ResultActions resultadoAPI = mockMvc
		   .perform(MockMvcRequestBuilders.delete("/deleteCoco/{id}", cocoId) // Substitui {id} pelo
		                                                                      // ID real do objeto
		                                                                      // Coco
		      .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		
		// Imprime o retorno da API
		System.out
		   .println("Retorno da API: " + resultadoAPI.andReturn().getResponse().getContentAsString());
		
		// Desserializa o retorno da API para um objeto Coco
		String retornoApi = resultadoAPI.andReturn().getResponse().getContentAsString();
		
		// Verifica se o atributo Cheiro do objeto Coco retornado
		// pela API é igual a "brabo"
		assertEquals("deletado com sucesso", retornoApi);
	}

	
}
