package com.eryusoft.core.controladores;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eryusoft.core.modelo.Tarea;
import com.eryusoft.core.modelo.TareaConstructor;
import com.eryusoft.core.repositorio.CommonRepository;
import com.eryusoft.core.validaciones.TareaValidacionError;
import com.eryusoft.core.validaciones.TareaValidacionErrorConstructor;

@RestController
@RequestMapping("/api")
public class TareaControlador {
	
	private CommonRepository<Tarea> repositorio;
	
	@Autowired
	public TareaControlador(CommonRepository<Tarea> prepositorio){
		this.repositorio = prepositorio;
	}
	
	@GetMapping("/tareas")
	public ResponseEntity<Iterable<Tarea>> getTareas(){
		return ResponseEntity.ok(repositorio.findAll());		
	}
	
	@GetMapping("/tarea/{id}")
	public ResponseEntity<Tarea> getTareaById(@PathVariable String id){
	return ResponseEntity.ok(repositorio.findById(id));
	}
	
	@RequestMapping(value="/todo", method = {RequestMethod.POST,
			RequestMethod.PUT})
	public ResponseEntity<?> createToDo(@Valid @RequestBody Tarea toDo,
			Errors perrores){
		if (perrores.hasErrors()) {
			return ResponseEntity.badRequest().
					body(TareaValidacionErrorConstructor.fromBindingErrors(perrores));
			}
			Tarea result = repositorio.save(toDo);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().
			path("/{id}")
			.buildAndExpand(result.getId()).toUri();
			return ResponseEntity.created(location).build();
			}
	
	@DeleteMapping("/tarea/{id}")
	public ResponseEntity<Tarea> deleteTarea(@PathVariable String id){
		repositorio.delete(TareaConstructor.crear().conId(id).construir());
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/tarea")
	public ResponseEntity<Tarea> deleteToDo(@RequestBody Tarea ptarea){
		repositorio.delete(ptarea);
		return ResponseEntity.noContent().build();
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public TareaValidacionError handleException(Exception exception) {
	return new TareaValidacionError(exception.getMessage());
	}

}
