package com.formaciondbi.microservicios.examenes.services;

import java.util.List;

import com.formaciondbi.microservicios.generics.examenes.Examen;
import com.formaciondbi.microservicios.generics.services.Services;

public interface ExamenServices extends Services<Examen, Long>{

	public List<Examen> findByNombreExamen(String term);
}
