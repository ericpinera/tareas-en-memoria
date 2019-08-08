package com.eryusoft.core.repositorio;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.eryusoft.core.modelo.Tarea;

@Repository
public class TareaRepositorio implements CommonRepository<Tarea>{
	private Map<String,Tarea> tareas = new HashMap<>();
	
	@Override
	public Tarea save(Tarea domain) {
		Tarea resultado = tareas.get(domain.getId());
		if(resultado != null) {
			resultado.setModificada(LocalDateTime.now());
			resultado.setDescripcion(domain.getDescripcion());
			resultado.setCompletada(domain.isCompletada());
			domain = resultado;
		}
		tareas.put(domain.getId(), domain);
		return tareas.get(domain.getId());
	}
	
	@Override
	public Iterable<Tarea> save(Collection<Tarea> domains){
		domains.forEach(this::save);
		return findAll();
	}
	
	@Override
	public void delete(Tarea domain) {
		tareas.remove(domain.getId());
	}
	
	@Override
	public Tarea findById(String id) {
		return tareas.get(id);
	}
	
	@Override
	public Iterable<Tarea> findAll() {
		return tareas.entrySet().stream().sorted(entryComparator).
				map(Map.Entry::getValue).collect(Collectors.toList());
	}
	
	private Comparator<Map.Entry<String,Tarea>> entryComparator = (Map.
			Entry<String, Tarea> o1, Map.Entry<String, Tarea> o2) -> {
				return o1.getValue().getCreada().compareTo(o2.getValue().
						getCreada());
	};
	
	
	
	
	

}
