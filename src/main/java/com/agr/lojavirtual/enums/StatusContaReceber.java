package com.agr.lojavirtual.enums;

public enum StatusContaReceber {

	COBRANCA("pagar"), VENCIDA("Vencida"), ABERTA("Aberta"), QITADA("Quitada");

	private String descricao;

	private StatusContaReceber(String descricao) {
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
