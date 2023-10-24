package com.agr.lojavirtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "cup_desc")
@SequenceGenerator(name = "seq_cup_desc", sequenceName = "seq_cup_desc", allocationSize = 1, initialValue = 1)
public class CupDesc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cup_desc")
	private Long id;
	private String codDesc;
	private BigDecimal valorRealDesc;
	private BigDecimal valorProcentDesc;

	@Temporal(TemporalType.DATE)
	private Date dtValidadeCupom;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodDesc() {
		return codDesc;
	}

	public void setCodDesc(String codDesc) {
		this.codDesc = codDesc;
	}

	public BigDecimal getValorRealDesc() {
		return valorRealDesc;
	}

	public void setValorRealDesc(BigDecimal valorRealDesc) {
		this.valorRealDesc = valorRealDesc;
	}

	public BigDecimal getValorProcentDesc() {
		return valorProcentDesc;
	}

	public void setValorProcentDesc(BigDecimal valorProcentDesc) {
		this.valorProcentDesc = valorProcentDesc;
	}

	public Date getDtValidadeCupom() {
		return dtValidadeCupom;
	}

	public void setDtValidadeCupom(Date dtValidadeCupom) {
		this.dtValidadeCupom = dtValidadeCupom;
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
		CupDesc other = (CupDesc) obj;
		return Objects.equals(id, other.id);
	}

}
