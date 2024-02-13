package com.agr.lojavirtual.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agr.lojavirtual.ExceptionLojaVirtual;
import com.agr.lojavirtual.model.Acesso;
import com.agr.lojavirtual.repository.AcessoRepository;

@Service
public class AcessoService {

	@Autowired
	private AcessoRepository acessoRepository;


	public Acesso salvar(Acesso acesso) throws ExceptionLojaVirtual {
		
		if (acesso.getDescricao().isEmpty()) {
			throw new ExceptionLojaVirtual("Acesso inválido: descrição vazia.");
		}
		return Optional.ofNullable(acesso)
		   .filter(a -> Objects.nonNull(a.getDescricao()) && !a.getDescricao().isEmpty())
		   .filter(a -> acessoRepository.buscarAcessoDesc(a.getDescricao()).isEmpty())
		   .map(a -> acessoRepository.save(a)).orElse(null);
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
		return acessoRepository.buscarAcessoDesc(desc);
	}

}
