package com.agr.lojavirtual.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agr.lojavirtual.repository.UsuarioRepository;

@Service
public class ImplementacaoUserDetailService implements UserDetailsService {
	
	@Autowired
	UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return Optional.ofNullable(repository.findUserByLogin(username))
		   .map(user -> new User(user.getLogin(), user.getPassword(), user.getAuthorities()))
		   .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
		
//		Usuario usuario = repository.findUserByLogin(username);
//		
//		if (usuario == null) {
//			throw new UsernameNotFoundException("Usuario não encontrado");
//		}
//			return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
	}
	
}
