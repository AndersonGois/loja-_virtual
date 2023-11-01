package com.agr.lojavirtual.security;

import javax.servlet.http.HttpSessionActivationListener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;



//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class WebConfigSecurity  {
//
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//	        http
//	            .authorizeRequests(authorizeRequests ->
//	                authorizeRequests
//	                .antMatchers(HttpMethod.DELETE, "/deleteAcesso").permitAll()
//	                    .anyRequest().fullyAuthenticated()
//	            );
//	        
////	        .antMatchers(HttpMethod.GET, "/salvarAcesso")
////            .antMatchers(HttpMethod.POST, "/salvarAcesso").antMatchers(HttpMethod.DELETE, "/deleteAcesso")
//	            
//	        return http.build();
//	    }
//
//}

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter implements HttpSessionActivationListener {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.GET, "/salvarAcesso").antMatchers(HttpMethod.POST, "/salvarAcesso").antMatchers(HttpMethod.DELETE, "/deleteAcesso");
	}

}
