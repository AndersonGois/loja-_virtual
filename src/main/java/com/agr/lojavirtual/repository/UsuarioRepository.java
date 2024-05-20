package com.agr.lojavirtual.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agr.lojavirtual.model.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
//	@Query(value = "select u from Usuario u where u.login =?1")
	@Query(value = "select u from Usuario u where u.login =:login")
	Usuario findUserByLogin(@Param("login") String login);

	@Query(value = "select u from Usuario u where u.login =:login")
	String consultaContraintRole();
	
}
