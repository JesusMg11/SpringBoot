package com.balamcode.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balamcode.modelos.Jugador;
import com.balamcode.servicio.JugadorServicio;

@RestController
@RequestMapping("/jugador/")
public class JugadorREST {

	@Autowired
	private JugadorServicio jugadorServicio; 
	
	@GetMapping
	private ResponseEntity<List<Jugador>> getAllJugadores(){
		return ResponseEntity.ok(jugadorServicio.findAll());
	}
	
	@PostMapping
	private ResponseEntity<Jugador> saveJugador(@ModelAttribute Jugador jugador){
		
		try {
			Jugador jugadorGuardado = jugadorServicio.save(jugador);
			System.out.println(ResponseEntity.created(new URI("/jugador/" + jugadorGuardado.getId())).body(jugadorGuardado));
			return ResponseEntity.created(new URI("/jugador/" + jugadorGuardado.getId())).body(jugadorGuardado);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@PutMapping
	private ResponseEntity<Jugador> updateJugador(@ModelAttribute Jugador jugador){
		
		try {
			Jugador jugadorGuardado = jugadorServicio.save(jugador);
			return ResponseEntity.created(new URI("/jugador/" + jugadorGuardado.getId())).body(jugadorGuardado);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@DeleteMapping (value= "eliminar/{id}")
	private ResponseEntity<Boolean> deleteJugador(@PathVariable ("id") int idJugador){
		jugadorServicio.deleteById(idJugador);
		return ResponseEntity.ok(!(jugadorServicio.findById(idJugador) != null));
	}
}

