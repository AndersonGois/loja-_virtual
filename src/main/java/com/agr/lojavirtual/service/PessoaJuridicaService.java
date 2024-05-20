package com.agr.lojavirtual.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agr.lojavirtual.ExceptionLojaVirtual;
import com.agr.lojavirtual.model.Pessoa;
import com.agr.lojavirtual.model.PessoaJuridica;
import com.agr.lojavirtual.repository.PessoaRepository;

@Service
public class PessoaJuridicaService {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	private boolean checkCampoVazio(PessoaJuridica pj) throws ExceptionLojaVirtual {
		if (pj.getNome().isEmpty()) {
			throw new ExceptionLojaVirtual("Campo Nome é obrigatório");
		}
		if (pj.getEmail().isEmpty()) {
			throw new ExceptionLojaVirtual("Campo Email é obrigatório");
		}
//		if (pj.getNome().isEmpty()) {
//			return new ExceptionLojaVirtual("Campo " + campo + " é obrigatório");
//		}
//		if (pj.getNome().isEmpty()) {
//			return new ExceptionLojaVirtual("Campo " + campo + " é obrigatório");
//		}
//		if (pj.getNome().isEmpty()) {
//			return new ExceptionLojaVirtual("Campo " + campo + " é obrigatório");
//		}
//		if (pj.getNome().isEmpty()) {
//			return new ExceptionLojaVirtual("Campo " + campo + " é obrigatório");
//		}
		return Boolean.FALSE;
	}

	public PessoaJuridica salvar(PessoaJuridica pessoa) throws ExceptionLojaVirtual {
		
		return Optional.ofNullable(pessoa)
		   .filter(pj -> !pj.getNome().isEmpty()).filter(pj -> !pj.getEmail().isEmpty())
		   .filter(pj -> !pj.getTelefone().isEmpty()).filter(pj -> !pj.getCnpj().isEmpty())
		   .filter(pj -> !pj.getNomeFantasia().isEmpty())
		   .filter(pj -> pessoaRepository.existeCnpj(pj.getCnpj()).isEmpty())
		   .map(pj -> (PessoaJuridica) pessoaRepository.save(pessoa))
		   .orElseThrow(() -> new ExceptionLojaVirtual("Favor preencher os cmpos obrigatórios"));
		
	}


	public void delete(Pessoa pessoa) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	public Pessoa obterPessoaPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Pessoa> obterPessoaPordesc(String desc) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
