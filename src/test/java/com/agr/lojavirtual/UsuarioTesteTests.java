//package com.agr.lojavirtual;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.Calendar;
//import java.util.List;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Profile;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.agr.lojavirtual.model.UsuarioTeste;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@Profile("dev")
//@SpringBootTest(classes = LojaVirtualApplication.class)
//public class UsuarioTesteTests {
//	
//	@Autowired
//	WebApplicationContext wac;
//	private UsuarioTeste user;
//	
//	@BeforeEach
//	public void init() throws Exception {
//		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
//		MockMvc mockMvc = builder.build();
//		// Cria um novo objeto UsuarioTeste
//		UsuarioTeste novoUsuarioTeste = new UsuarioTeste();
//		novoUsuarioTeste.setNome("Bom" + Calendar.getInstance().getTimeInMillis());
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		// Salva o novo objeto UsuarioTeste
//		ResultActions resultadoSalvar = mockMvc.perform(MockMvcRequestBuilders.post("/salvarUsuarioTeste")
//		   .content(objectMapper.writeValueAsString(novoUsuarioTeste)).accept(MediaType.APPLICATION_JSON)
//		   .contentType(MediaType.APPLICATION_JSON));
//		
//		// Desserializa o retorno da API para um objeto UsuarioTeste
//		user = objectMapper.readValue(resultadoSalvar.andReturn().getResponse().getContentAsString(),
//		   UsuarioTeste.class);
//	}
//	
//	@AfterEach
//	public void aftercada() throws Exception {
//		System.out.println("AfterEach " + user.getId());
//		// ID do objeto UsuarioTeste que você deseja deletar
//		Long userId = user.getId();
//		System.out.println("TODO: _id " + user.getId());
//		// Cria um MockMvc a partir do contexto da aplicação web
//		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
//		MockMvc mockMvc = builder.build();
//		
//		// Define um ObjectMapper para serializar e desserializar
//		// objetos em JSON
//		ObjectMapper mapper = new ObjectMapper();
//		
//		// Realiza uma requisição DELETE para a rota
//		// /deleteUsuarioTeste/{id}
//		ResultActions resultadoAPI = mockMvc
//		   .perform(MockMvcRequestBuilders.delete("/deleteUsuarioTeste/{id}", userId) // Substitui
//		                                                                              // {id} pelo
//		                                                                      // ID real do objeto
//		                                                                      // UsuarioTeste
//		      .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
//		
//		// Imprime o retorno da API
//		System.out
//		   .println("Retorno da API: " + resultadoAPI.andReturn().getResponse().getContentAsString());
//	}
//	
//	@Test
//	@Order(1)
//	public void testeRestCadastroUsuarioTeste() throws JsonProcessingException, Exception {
//		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
//		MockMvc mockMvc = builder.build();
//		
//		UsuarioTeste user = new UsuarioTeste();
//		user.setNome("Bom" + Calendar.getInstance().getTimeInMillis());
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.post("/salvarUsuarioTeste")
//		   .content(objectMapper.writeValueAsString(user)).accept(MediaType.APPLICATION_JSON)
//		   .contentType(MediaType.APPLICATION_JSON));
//		
//		System.out
//		   .println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
//		
//		UsuarioTeste objetoRetorno = objectMapper
//		   .readValue(retornoApi.andReturn().getResponse().getContentAsString(), UsuarioTeste.class);
//		
//		System.out.println("salvar" + user.getId());
//		assertEquals(true, objetoRetorno.getId() > 0);
//		assertEquals(user.getNome(), objetoRetorno.getNome());
//		
//	}
//	
//	@Test
//	@Order(2)
//	public void testeTodosUsuarioTestes() throws JsonProcessingException, Exception {
//		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
//		MockMvc mockMvc = builder.build();
//
//		
//		ObjectMapper mapper = new ObjectMapper();
//		ResultActions retornoApi = mockMvc
//		   .perform(MockMvcRequestBuilders.get("/todosUsuarioTestes")
//		      .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
//		
//		System.out
//		   .println("TODO: Retorno API" + retornoApi.andReturn().getResponse().getContentAsString());
//		
//		List<UsuarioTeste> users = mapper.readValue(
//		   retornoApi.andReturn().getResponse().getContentAsString(),
//		   new TypeReference<List<UsuarioTeste>>() {
//		   });
//		
//		assertEquals(true, users.size() > 0);
//		
//	}
//	
//	@Test
//	@Order(3)
//	public void testAlterarUsuarioTeste() throws JsonProcessingException, Exception {
//		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
//		MockMvc mockMvc = builder.build();
//		System.out.println("testAlterarUsuarioTeste " + user.getId());
//		UsuarioTeste co = new UsuarioTeste();
//		co.setId(user.getId());
//		co.setNome("brabo");
//		co.setIdade(19);
//		
//		ObjectMapper mapper = new ObjectMapper();
//		
//		ResultActions resultadoAPI = mockMvc
//		   .perform(MockMvcRequestBuilders.put("/alterarUsuarioTeste").content(mapper.writeValueAsString(co))
//		      .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
//		
//		System.out.println(
//		   "TODO: retorno da api" + resultadoAPI.andReturn().getResponse().getContentAsString());
//		UsuarioTeste retotnoApi = mapper
//		   .readValue(resultadoAPI.andReturn().getResponse().getContentAsString(), UsuarioTeste.class);
//		
//		assertEquals(true, retotnoApi.getNome().equals("brabo"));
//		assertEquals(co.getNome(), retotnoApi.getNome());
//		
//	}
//	
//	
//
//	@Test
//	@Order(4)
//	public void testDeleteUsuarioTeste() throws Exception {
//		System.out.println("testDeleteUsuarioTeste " + user.getId());
//		// ID do objeto UsuarioTeste que você deseja deletar
//		Long userId = user.getId();
//		System.out.println("TODO: _id " + user.getId());
//		// Cria um MockMvc a partir do contexto da aplicação web
//		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(wac);
//		MockMvc mockMvc = builder.build();
//		
//		// Define um ObjectMapper para serializar e desserializar
//		// objetos em JSON
//		ObjectMapper mapper = new ObjectMapper();
//		
//		// Realiza uma requisição DELETE para a rota
//		// /deleteUsuarioTeste/{id}
//		ResultActions resultadoAPI = mockMvc
//		   .perform(MockMvcRequestBuilders.delete("/deleteUsuarioTeste/{id}", userId) // Substitui
//		                                                                              // {id} pelo
//		                                                                      // ID real do objeto
//		                                                                      // UsuarioTeste
//		      .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
//		
//		// Imprime o retorno da API
//		System.out
//		   .println("Retorno da API: " + resultadoAPI.andReturn().getResponse().getContentAsString());
//		
//		// Desserializa o retorno da API para um objeto UsuarioTeste
//		String retornoApi = resultadoAPI.andReturn().getResponse().getContentAsString();
//		
//		// Verifica se o atributo Nome do objeto UsuarioTeste retornado
//		// pela API é igual a "brabo"
//		assertEquals("deletado com sucesso", retornoApi);
//	}
//
//	
//}
