package com.agr.lojavirtual.service;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agr.lojavirtual.ExceptionLojaVirtual;
import com.agr.lojavirtual.model.UsuarioTeste;
import com.agr.lojavirtual.repository.UsuarioTesteRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioTesteRepository userTesteRepository;
	
	public UsuarioTeste salvar(UsuarioTeste user) throws ExceptionLojaVirtual {
		
		if (user.getNome().isEmpty()) {
			throw new ExceptionLojaVirtual("prenencha o nome do usuario");
		}
		return userTesteRepository.save(user);
	}
	
	public UsuarioTeste alterar(UsuarioTeste user) throws ExceptionLojaVirtual {
		
		if (user.getNome().isEmpty()) {
			throw new ExceptionLojaVirtual("prenencha o nome do usuario");
		}
		UsuarioTeste usuario = userTesteRepository.findById(user.getId())
		   .orElseThrow(() -> new ExceptionLojaVirtual("Usuário não encontrado"));
		
		// Utilizando Reflection para atualizar as propriedades
		for (Field field : UsuarioTeste.class.getDeclaredFields()) {
			field.setAccessible(true);
			try {
				Object newValue = field.get(user);
				if (newValue != null) {
					field.set(usuario, newValue);
				}
			} catch (IllegalAccessException e) {
				// Tratar exceções de acesso
			}
		}
		
		return userTesteRepository.save(usuario);
	}
	
	public List<UsuarioTeste> todosUsuarios() {
		
		return userTesteRepository.findAll();
	}
	
	public void delete(Long id) throws ExceptionLojaVirtual {
		try {
			userTesteRepository.deleteById(id);
			
		} catch (Exception e) {
			throw new ExceptionLojaVirtual(String.format("Não existe coco com o id %d informado", id));
		}
	}
	
}
