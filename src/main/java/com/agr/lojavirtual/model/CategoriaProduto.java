package com.agr.lojavirtual.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "categoria_produto")
@SequenceGenerator(name = "seq_categoria_produto" , sequenceName = "seq_categoria_produto" ,allocationSize = 1, initialValue = 1)
public class CategoriaProduto  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_categoria_produto")
	private long id;
	
	@Column(name = "nome_desc" ,nullable = false)
	private String nomeDesc;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeDesc() {
		return nomeDesc;
	}
	public void setNomeDesc(String nomeDesc) {
		this.nomeDesc = nomeDesc;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, nomeDesc);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaProduto other = (CategoriaProduto) obj;
		return id == other.id && Objects.equals(nomeDesc, other.nomeDesc);
	}
	
	
}
