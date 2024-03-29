package com.agr.lojavirtual.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/*Filtro onde todas as requisições ser~çao capituradas para autenticar  */
public class JwtApiAutenticacaoFilter extends GenericFilterBean {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	   throws IOException, ServletException {
		/* estabelecer a autenticacao do usuario */
		
		Authentication authentication = null;
		try {
			authentication = new JWTTokenAutenticacaoService()
			   .getAuthetication((HttpServletRequest) request, (HttpServletResponse) response);
			/*
			 * Coloca o processo de autenticação para o spring security
			 */
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			chain.doFilter(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			response.getWriter()
			   .write("Ocorreu erro no sistema avise o administrador: \n" + e.getMessage());
		}
		
		
	}
	
}
