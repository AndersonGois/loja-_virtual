package com.agr.lojavirtual.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "coco")
@SequenceGenerator(name = "seq_coco", sequenceName = "seq_coco", allocationSize = 1, initialValue = 1)
public class Coco {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_coco")
	private Long id;
	
	@Column(nullable = false)
	private String cheiro;
	
	@Column(nullable = false)
	private String dono;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCheiro() {
		return cheiro;
	}
	
	public void setCheiro(String cheiro) {
		this.cheiro = cheiro;
	}
	
	public String getDono() {
		return dono;
	}
	
	public void setDono(String dono) {
		this.dono = dono;
	}
	
}
