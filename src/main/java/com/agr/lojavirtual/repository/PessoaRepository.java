package com.agr.lojavirtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agr.lojavirtual.model.Pessoa;
import com.agr.lojavirtual.model.PessoaJuridica;

@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
	
	@Query("select a from PessoaJuridica a where a.cnpj = ?1")
	List<PessoaJuridica> existeCnpj(String cnpj);
	



}
