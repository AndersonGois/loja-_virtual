package com.agr.lojavirtual.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.agr.lojavirtual.ApplicationContextLoad;
import com.agr.lojavirtual.model.Usuario;
import com.agr.lojavirtual.repository.UsuarioRepository;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
@Component
public class JWTTokenAutenticacaoService {
	
	/* Token de validade de 11 dias */
	private static final long EXPIRATION_TIME = 959990000;
	private static final String SECRET = "SS/-*-DFDFDFD5252";
	private static final String TOKEN_PRIFIX = "Bearer";
	private static final String HEADER_STRING = "Authorization";
	
	/* Gera o token e da a resposta para o cliente com JWT */
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {
		
		String JWT = Jwts.builder()/* Chama o gerador de token */
		   .setSubject(username)/* Adiciona o User */
		   .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
		   .signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		String token = TOKEN_PRIFIX + " " + JWT;
		
		/* Dá resposta para o cliente */
		response.addHeader(HEADER_STRING, token);
		
		liberacaoCors(response);
		
		/* usado para ver no postman */
		response.getWriter().write("{\"Authorization\":\"" + token + "\"}");
	}
	
	/*
	 * retornar o usuario validade com tokem caso são seja
	 * validado retornar null
	 */
	public Authentication getAuthetication(HttpServletRequest request, HttpServletResponse response)
	   throws Exception {
		
		String token = request.getHeader(HEADER_STRING);
		try {
			if (token != null) {
				String tokenLimpo = token.replace(TOKEN_PRIFIX, "").trim();
				
				/* Faz a validação do usuario na requisição */
				
				String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(tokenLimpo).getBody()
				   .getSubject();/* usuario */
				
				if (user != null) {
					Usuario usuario = ApplicationContextLoad.getApplicationContext()
					   .getBean(UsuarioRepository.class).findUserByLogin(user);
					
					if (usuario != null) {
						return new UsernamePasswordAuthenticationToken(usuario.getLogin(),
						   usuario.getPassword(), usuario.getAuthorities());
					}
				}
			}
			
		} catch (SignatureException e) {
			response.getWriter().write("Token esta inválido");
		} catch (ExpiredJwtException ex) {
			response.getWriter().write("Token está expirado, efetue o login novamente");
		} finally {
			liberacaoCors(response);
		}
		
		return null;
		
	}
	
	/* Fazendo liberação contra erro de Cors no navegador */
	private void liberacaoCors(HttpServletResponse response) {
		
		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
	}
	
}
