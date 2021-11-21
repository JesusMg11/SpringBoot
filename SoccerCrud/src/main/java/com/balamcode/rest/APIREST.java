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

import com.balamcode.modelos.Equipo;
import com.balamcode.modelos.Jugador;
import com.balamcode.servicio.EquipoServicio;
import com.balamcode.servicio.JugadorServicio;

@RestController
@RequestMapping("/api/")
public class APIREST {

	@Autowired
	private EquipoServicio equipoServicio;
	@Autowired
	private JugadorServicio jugadorServicio; 
	
	//REQUEST PARA EQUIPOS
	
	@GetMapping(path="equipo/buscarJugadores/{id}")
	private ResponseEntity<List<Jugador>> getAllJugadoresByEquipo(@PathVariable("id") int id){
		return ResponseEntity.ok(jugadorServicio.findAllByEquipo(id));
	}
	
	@GetMapping(path="equipo/buscar")
	public ResponseEntity<List<Equipo>> getAllEquipos(){
		return ResponseEntity.ok(equipoServicio.findAll());
	}
	
	@PostMapping(path="equipo/agregar")
	private ResponseEntity<Equipo> saveEquipo(@ModelAttribute Equipo equipo){
		
		try {
			Equipo equipoGuardado = equipoServicio.save(equipo);
			return ResponseEntity.created(new URI("/equipo/" + equipo.getId())).body(equipoGuardado);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	//REQUEST PARA JUGADORES
	
	@GetMapping(path="jugador/buscar")
	private ResponseEntity<List<Jugador>> getAllJugadores(){
		return ResponseEntity.ok(jugadorServicio.findAll());
	}
	
	@PostMapping(path="jugador/agregar")
	private ResponseEntity<Jugador> saveJugador(@ModelAttribute Jugador jugador){
		
		try {
			Jugador jugadorGuardado = jugadorServicio.save(jugador);
			System.out.println(ResponseEntity.created(new URI("/jugador/" + jugadorGuardado.getId())).body(jugadorGuardado));
			return ResponseEntity.created(new URI("/jugador/" + jugadorGuardado.getId())).body(jugadorGuardado);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@PutMapping(path="jugador/actualizar")
	private ResponseEntity<Jugador> updateJugador(@ModelAttribute Jugador jugador){
		
		try {
			Jugador jugadorGuardado = jugadorServicio.save(jugador);
			return ResponseEntity.created(new URI("/jugador/" + jugadorGuardado.getId())).body(jugadorGuardado);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@DeleteMapping (path= "jugador/eliminar/{id}")
	private ResponseEntity<Boolean> deleteJugador(@PathVariable ("id") int idJugador){
		jugadorServicio.deleteById(idJugador);
		return ResponseEntity.ok(!(jugadorServicio.findById(idJugador) != null));
	}
	
}
