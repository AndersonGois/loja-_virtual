package com.agr.lojavirtual.security;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
		/* usado para ver no postman */
		response.getWriter().write("{\"Authorization\":\"" + token + "\"}");
	}
	
}
