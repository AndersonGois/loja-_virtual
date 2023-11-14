package com.agr.lojavirtual.service;

import java.util.List;

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

	public void delete(Long id) {
		acessoRepository.deleteById(id);

	}

	public Acesso obterAcessoPorId(Long id) {
		return acessoRepository.findById(id).orElse(null);
	}

	public List<Acesso> obterAcessoPordesc(String desc) {
		// TODO Auto-generated method stub
		return acessoRepository.buscarAcessoDesc(desc);
	}

}
