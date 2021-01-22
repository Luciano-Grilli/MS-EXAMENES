package com.formaciondbi.microservicios.examenes.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formaciondbi.microservicios.examenes.services.ExamenServices;
import com.formaciondbi.microservicios.generics.controllers.ControllerImpl;
import com.formaciondbi.microservicios.generics.examenes.Examen;
import com.formaciondbi.microservicios.generics.examenes.Pregunta;
import com.formaciondbi.microservicios.generics.services.ServicesImpl;

@RestController
public class ExamenController extends ControllerImpl<Examen, ServicesImpl<Examen,Long>>{

	@Autowired
	protected ExamenServices examenServices;
	//this.servicio o examenServices 
	
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Examen entity){
		
		Examen op;
		
		try {
			op = this.servicio.findById(id);
			
		} catch (Exception e) {
			System.out.println("------------murio--------------------------------------------------");
			
			return ResponseEntity.notFound().build();
		}	
		
		op.setNombre(entity.getNombre());;
		
		
		List<Pregunta> eliminadas = new ArrayList<>();
		
		op.getPreguntas().forEach(p ->{
			if (!entity.getPreguntas().contains(p)) {
				eliminadas.add(p);
			}
		});
		
		eliminadas.forEach(p ->{
			op.removePregunta(p);
		});
		
		op.setPreguntas(entity.getPreguntas());
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(op));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no anda");
			
		}
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(examenServices.findByNombreExamen(term));
	}
}
