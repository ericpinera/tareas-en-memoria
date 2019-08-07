package com.eryusoft.core.modelo;


import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;

@Data
@Setter @Getter
public class Tarea {
	
	@NotNull
	private String id;	
	@NotNull
	@NotBlank
	private String descripcion;
	private LocalDateTime creada;
	private LocalDateTime modificada;
	private boolean completada;
	
	public Tarea(){
		LocalDateTime fecha = LocalDateTime.now();
		this.id = UUID.randomUUID().toString();
		this.creada = fecha;
		this.modificada = fecha;		
	}
	
	public Tarea(String pdescripcion){
		this();
		this.descripcion = pdescripcion;
	}

}
