package com.agr.lojavirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agr.lojavirtual.ExceptionLojaVirtual;
import com.agr.lojavirtual.model.Coco;
import com.agr.lojavirtual.service.CocoService;

@Controller
@RestController
public class CocoController {
	
	@Autowired
	private CocoService cocoService;
	
	@ResponseBody
	@PostMapping(value = "/salvarCoco")
	public ResponseEntity<?> salvarCoco(@RequestBody Coco coco) throws ExceptionLojaVirtual {
		try {
			Coco cocoSalvo = cocoService.salvar(coco);
			return ResponseEntity.status(HttpStatus.OK).body(cocoSalvo);
			
		} catch (ExceptionLojaVirtual e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseBody
	@PutMapping(value = "/alterarCoco")
	public ResponseEntity<?> alterarCoco(@RequestBody Coco coco) throws ExceptionLojaVirtual {
		try {
			Coco cocoSalvo = cocoService.salvar(coco);
			return ResponseEntity.status(HttpStatus.OK).body(cocoSalvo);
			
		} catch (ExceptionLojaVirtual e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseBody
	@DeleteMapping(value = "/deleteCoco/{id}")
	public ResponseEntity<?> deleteCoco(@PathVariable Long id) throws ExceptionLojaVirtual {
		try {
			cocoService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("deletado com sucesso");
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseBody
	@GetMapping(value = "/todosCocos")
	public ResponseEntity<?> todosCocos() throws ExceptionLojaVirtual {
		List<Coco> todos = cocoService.todosCocos();
		System.out.println(todos);
		return ResponseEntity.status(HttpStatus.OK).body(todos);
	}

}
