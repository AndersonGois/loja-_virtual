package com.agr.lojavirtual;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.agr.lojavirtual.model.dto.ObjetoErroDTO;

@RestControllerAdvice
@ControllerAdvice
public class ControleExcecoes extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ExceptionLojaVirtual.class)
	public ResponseEntity<Object> handleExceptionCuston(ExceptionLojaVirtual ex) {
		ObjetoErroDTO dto = new ObjetoErroDTO();
		
		dto.setError(ex.getMessage());
		dto.setCode(HttpStatus.OK.toString());
		
		return new ResponseEntity<Object>(dto, HttpStatus.OK);
	}
	
	/* Captura excessões do projeto */
	@ExceptionHandler({ Exception.class, RuntimeException.class, Throwable.class })
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
	   HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ObjetoErroDTO dto = new ObjetoErroDTO();
		
		String msg = "";
		if (ex instanceof MethodArgumentNotValidException) {
			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult()
			   .getAllErrors();
			
//			for (ObjectError erro : list) {
//				msg += erro.getDefaultMessage() + "\n";
//				
//			}
			msg = list.stream().map(ObjectError::getDefaultMessage)
			   .reduce((acc, mensagem) -> acc + mensagem + "\n").orElse("");
			
		} else {
			msg = ex.getMessage();
		}
		dto.setError(msg);
		dto.setCode(status.value() + " ==> " + status.getReasonPhrase());
		
		ex.printStackTrace();
		
		return new ResponseEntity<Object>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	/* Captura erro da parte de banco */
	
	@ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class,
	      SQLException.class })
	protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex) {
		ObjetoErroDTO dto = new ObjetoErroDTO();
		
		String msg = "";
		
		String errorMessage = ex.getCause().getMessage();
		
		if (ex instanceof SQLException) {
			msg = "Erro de integridade no banco: " + errorMessage;
		} else if (ex instanceof DataIntegrityViolationException) {
			msg = "Erro de SQL do banco: " + errorMessage;
		} else if (ex instanceof ConstraintViolationException) {
			msg = "Erro de chave estrangeira:  " + errorMessage;
		}
		else {
			msg = ex.getMessage();
		}
		
		dto.setError(msg);
		dto.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		
		ex.printStackTrace();
		
		return new ResponseEntity<Object>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
