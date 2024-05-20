package com.agr.lojavirtual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.web.context.WebApplicationContext;

import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualApplication.class)
public class TestePessoaUsuario extends TestCase {
	

	@Autowired
	WebApplicationContext wac;
	
//	@Test
//	public void testCadUser() throws JsonProcessingException, Exception {
//		
//		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
//		MockMvc mockMvc = builder.build();
//		
//		PessoaJuridica pessoaJuridica = new PessoaJuridica();
//		pessoaJuridica.setCnpj("028345647792");
//		pessoaJuridica.setNome("Anderson2");
//		pessoaJuridica.setEmail("agoisrj@gmail.com");
//		pessoaJuridica.setTelefone("21988496958");
//		pessoaJuridica.setInscEstadual("21551181");
//		pessoaJuridica.setInscMunicipal("454545444");
//		pessoaJuridica.setNomeFantasia("Agr Soluçoes");
//		pessoaJuridica.setRazaoSocial("Agr Soluçoes");
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.post("/salvarPJ")
//		   .content(objectMapper.writeValueAsString(pessoaJuridica))
//		   .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
//		
//		System.out
//		   .println("Retorno da API: " + retornoApi.andReturn().getResponse().getContentAsString());
//		
//		PessoaJuridica objetoRetorno = objectMapper.readValue(
//		   retornoApi.andReturn().getResponse().getContentAsString(), PessoaJuridica.class);
//
//		assertEquals(true, objetoRetorno.getId() > 0);
//		assertEquals(pessoaJuridica.getNome(), objetoRetorno.getNome());
//		
//	}
	
}
