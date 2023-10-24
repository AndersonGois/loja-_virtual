package com.agr.lojavirtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Produto")
@SequenceGenerator(name = "seq_produto", sequenceName ="seq_produto", initialValue = 1,allocationSize = 1 )
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "seq_produto", strategy = GenerationType.SEQUENCE)
	private Long id;
	private String tipoUnidade;
	private String nome;
	@Column(columnDefinition = "text", length = 2000)
	private String descricao;
	private String linkYoutube;
	private Double peso;
	private Double largura;
	private Double profundidade;
	private BigDecimal valorVenda = BigDecimal.ZERO;
	private Integer qtdEstoque =0;
	private Integer qtdeClique =0;
	private Integer qtdeAlertaEstoque = 0;
	private Boolean alertaqtdeEstoque =Boolean.FALSE;
	private Boolean ativo =Boolean.TRUE;
	
	
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoUnidade() {
		return tipoUnidade;
	}
	public void setTipoUnidade(String tipoUnidade) {
		this.tipoUnidade = tipoUnidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getLinkYoutube() {
		return linkYoutube;
	}
	public void setLinkYoutube(String linkYoutube) {
		this.linkYoutube = linkYoutube;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getLargura() {
		return largura;
	}
	public void setLargura(Double largura) {
		this.largura = largura;
	}
	public Double getProfundidade() {
		return profundidade;
	}
	public void setProfundidade(Double profundidade) {
		this.profundidade = profundidade;
	}
	public BigDecimal getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
	public Integer getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public Integer getQtdeClique() {
		return qtdeClique;
	}
	public void setQtdeClique(Integer qtdeClique) {
		this.qtdeClique = qtdeClique;
	}
	public Integer getQtdeAlertaEstoque() {
		return qtdeAlertaEstoque;
	}
	public void setQtdeAlertaEstoque(Integer qtdeAlertaEstoque) {
		this.qtdeAlertaEstoque = qtdeAlertaEstoque;
	}
	public Boolean getAlertaqtdeEstoque() {
		return alertaqtdeEstoque;
	}
	public void setAlertaqtdeEstoque(Boolean alertaqtdeEstoque) {
		this.alertaqtdeEstoque = alertaqtdeEstoque;
	}
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
	
	/* Nota item nota  produto - associar */
	

}
