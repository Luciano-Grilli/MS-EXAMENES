package com.formaciondbi.microservicios.examenes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formaciondbi.microservicios.examenes.repository.ExamenRepository;
import com.formaciondbi.microservicios.generics.examenes.Examen;
import com.formaciondbi.microservicios.generics.services.ServicesImpl;

@Service
public class ExamenServicesImpl extends ServicesImpl<Examen, Long> implements ExamenServices{

	@Autowired
	protected ExamenRepository examenRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombreExamen(String term) {
		
		return examenRepository.findByNombreExamen(term);
	}

}
