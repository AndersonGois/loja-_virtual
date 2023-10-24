package com.agr.lojavirtual.enums;

public enum StatusContaPagar {

	COBRANCA("pagar"), 
	VENCIDA("Vencida"), 
	ABERTA("Aberta"), 
	QUITADA("Quitada"),
	ALUGUEL("Aluguel"),
	FUNCIONARIO("funcionário"),
	NEGOCIADA("Renegociada");

	private String descricao;

	private StatusContaPagar(String descricao) {
		this.descricao = descricao;

	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return this.descricao;
	}

}
