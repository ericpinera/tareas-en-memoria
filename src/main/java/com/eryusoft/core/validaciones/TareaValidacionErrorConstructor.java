package com.eryusoft.core.validaciones;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class TareaValidacionErrorConstructor {
	
	public static TareaValidacionError fromBindingErrors(Errors errores) {
		TareaValidacionError error = new TareaValidacionError("Validación failed. " + 
				errores.getErrorCount() + " error(es)");
		
		for (ObjectError objectError : errores.getAllErrors()) {
			error.addValidationError(objectError.getDefaultMessage());
		}
		return error;
		}

}
