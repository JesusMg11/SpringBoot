package com.balamcode.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balamcode.modelos.Equipo;
import com.balamcode.servicio.EquipoServicio;

@RestController
@RequestMapping("/equipo/")
public class EquipoREST {

	@Autowired
	private EquipoServicio equipoServicio;
	
	@GetMapping
	public ResponseEntity<List<Equipo>> getAllEquipos(){
		return ResponseEntity.ok(equipoServicio.findAll());
	}
	
	@PostMapping
	private ResponseEntity<Equipo> saveEquipo(@ModelAttribute Equipo equipo){
		
		try {
			Equipo equipoGuardado = equipoServicio.save(equipo);
			return ResponseEntity.created(new URI("/equipo/" + equipo.getId())).body(equipoGuardado);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
}
