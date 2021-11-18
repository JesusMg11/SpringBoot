package com.balamcode.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balamcode.modelos.Jugador;
import com.balamcode.servicio.JugadorServicio;

@RestController
@RequestMapping("/jugadores/")
public class JugadoresREST {

	@Autowired
	private JugadorServicio jugadorServicio; 
	
	@GetMapping("{id}")
	private ResponseEntity<List<Jugador>> getAllJugadoresByEquipo(@PathVariable("id") int id){
		return ResponseEntity.ok(jugadorServicio.findAllByEquipo(id));
	}
}
