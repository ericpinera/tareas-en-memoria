package com.eryusoft.core.modelo;

public class TareaConstructor {
	
	private static TareaConstructor instancia =  new TareaConstructor();
	private String id = null;
	private String descripcion = "";
	
	private TareaConstructor() {}
	
	public static TareaConstructor crear(){
		return instancia;
	}
	
	public TareaConstructor conDescripcion(String pdescripcion){
		this.descripcion = pdescripcion;
		return instancia;
	}
	
	public TareaConstructor conId(String pId){
		this.id = pId;
		return instancia;
	}
	
	public Tarea construir(){
		Tarea resultado = new Tarea(this.descripcion);
		if (id != null)
			resultado.setId(id);
		return resultado;
	}
	

}
