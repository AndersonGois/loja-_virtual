package com.agr.lojavirtual.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.agr.lojavirtual.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	public JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
		/* Obriga a autenticar a url */
		super(new AntPathRequestMatcher(url));
		
		/* Gerenciador de autenticação */
		setAuthenticationManager(authenticationManager);
	}
	
	protected JWTLoginFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
		// TODO Auto-generated constructor stub
	}
	
	/* Retorna o usuario ao processar a autenticação */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
	   HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		
		/* obter usuario */
		Usuario user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
		/* retorna user com login e senha */
		return getAuthenticationManager()
		   .authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
	   FilterChain chain, Authentication authResult) throws IOException, ServletException {
		
		try {
			new JWTTokenAutenticacaoService().addAuthentication(response, authResult.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
	   HttpServletResponse response, AuthenticationException failed)
	   throws IOException, ServletException {
		if (failed instanceof BadCredentialsException) {
			response.getWriter().write("User e senha não encontrado");
		} else {
			response.getWriter().write("falha ao logar: " + failed.getMessage());
		}
		// super.unsuccessfulAuthentication(request, response,
		// failed);
	}
	
}
