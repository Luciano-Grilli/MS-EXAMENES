package com.formaciondbi.microservicios.examenes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.formaciondbi.microservicios.generics.examenes.Examen;
import com.formaciondbi.microservicios.generics.repository.Repository;

public interface ExamenRepository extends Repository<Examen, Long>{
	
	@Query("select e from Examen  e where e.nombre like %?1%")
	public List<Examen> findByNombreExamen(String term);

}
