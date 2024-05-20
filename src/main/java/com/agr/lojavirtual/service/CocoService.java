package com.agr.lojavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agr.lojavirtual.ExceptionLojaVirtual;
import com.agr.lojavirtual.model.Coco;
import com.agr.lojavirtual.repository.CocoRepository;

@Service
public class CocoService {
	
	@Autowired
	private CocoRepository cocoRepository;
	
	public Coco salvar(Coco coco) throws ExceptionLojaVirtual {
		
		if (coco.getDono().isEmpty()) {
			throw new ExceptionLojaVirtual("prenencha o dono do coco");
		}
		return cocoRepository.save(coco);
	}
	
	public List<Coco> todosCocos() {
		
		return cocoRepository.findAll();
	}
	
	public void delete(Long id) throws ExceptionLojaVirtual {
		try {
			cocoRepository.deleteById(id);
			
		} catch (Exception e) {
			throw new ExceptionLojaVirtual(
			   String.format("Não existe coco com o id %d informado", id));
		}
	}
	
	

}
