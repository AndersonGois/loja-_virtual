package com.agr.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agr.lojavirtual.model.Acesso;
import com.agr.lojavirtual.repository.AcessoRepository;

@Service
public class AcessoService {

	@Autowired
	private AcessoRepository acessoRepository;

	public Acesso save(Acesso acesso) {
		return acessoRepository.save(acesso);

	}

	public void delete(Acesso acesso) {
		 acessoRepository.deleteById(acesso.getId());
	}

}
