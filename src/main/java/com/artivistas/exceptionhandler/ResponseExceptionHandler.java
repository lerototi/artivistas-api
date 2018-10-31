package com.artivistas.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String messageUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String messageDevelopment = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDevelopment));
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> erros = createListError(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	private List<Erro> createListError(BindingResult bindingResult){
		List<Erro> erros = new ArrayList<>();
		
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String messageUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String messageDevelopment= fieldError.toString();
			
			erros.add(new Erro(messageUser, messageDevelopment));
		
		}
		return erros;
	}
	
	
	public static class Erro{
		
		private String messageUser;
		private String messageDevelopment;
		
		public Erro(String messageUser, String messageDevelopment) {
			this.messageUser = messageUser;
			this.messageDevelopment = messageDevelopment;
		}

		public String getMessageUser() {
			return messageUser;
		}

		public String getMessageDevelopment() {
			return messageDevelopment;
		}
		
		
		
		
	}
	
}
