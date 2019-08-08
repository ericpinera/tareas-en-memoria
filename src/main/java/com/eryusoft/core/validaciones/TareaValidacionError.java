package com.eryusoft.core.validaciones;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class TareaValidacionError {
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<String> errores = new ArrayList<>();
	
	private final String errorMensaje;
	
	public TareaValidacionError(String perrorMensaje) {
		this.errorMensaje = perrorMensaje;
	}
	
	public void addValidationError(String error) {
		errores.add(error);
	}
	
	public List<String> getErrores() {
		return errores;
	}
		
	public String getErrorMessage() {
		return errorMensaje;
	}
	

}
