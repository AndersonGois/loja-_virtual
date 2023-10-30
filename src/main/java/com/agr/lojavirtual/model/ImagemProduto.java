package com.agr.lojavirtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "imagem_produto")
@SequenceGenerator(name = "seq_produto", sequenceName ="seq_imagem_produto", initialValue = 1,allocationSize = 1 )
public class ImagemProduto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_imagem_produto", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	
	@Column(columnDefinition = "text" ,nullable = false)
	private String imagemOriginal;
	
	@Column(columnDefinition = "text", nullable = false)
	private String imagemMiniatura;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getImagemOriginal() {
		return imagemOriginal;
	}


	public void setImagemOriginal(String imagemOriginal) {
		this.imagemOriginal = imagemOriginal;
	}


	public String getImagemMiniatura() {
		return imagemMiniatura;
	}


	public void setImagemMiniatura(String imagemMiniatura) {
		this.imagemMiniatura = imagemMiniatura;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	
	@ManyToOne(targetEntity = Produto.class)
	@JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "produto_fk"))
	private Produto produto;
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImagemProduto other = (ImagemProduto) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

}
