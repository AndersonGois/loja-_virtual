package com.agr.lojavirtual.security;

import javax.servlet.http.HttpSessionActivationListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.agr.lojavirtual.service.ImplementacaoUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter implements HttpSessionActivationListener {

	@Autowired
	private ImplementacaoUserDetailService detailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).disable()
		   .authorizeRequests().antMatchers("/").permitAll().antMatchers("/index").permitAll()
		   .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		   /*
		    * redireciona ou da um retornno para index quando desloga
		    */
		   .anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
		   
		   /* mapeia o logout */
		   .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		   
		   /* filtra as requisições para login de JWT */
		   .and()
		   .addFilterAfter(new JWTLoginFilter("/login", authenticationManager()),
		      UsernamePasswordAuthenticationFilter.class)
		   .addFilterBefore(new JwtApiAutenticacaoFilter(),
		      UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.GET, "/obterAcessoPorId/*")
		   .antMatchers(HttpMethod.POST, "/salvarAcesso")
		   .antMatchers(HttpMethod.DELETE, "/deleteAcesso");
	}
	
	/* Ir[a consultar o user no banco */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
