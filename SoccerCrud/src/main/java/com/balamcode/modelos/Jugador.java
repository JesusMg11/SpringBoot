package com.balamcode.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name = "jugador")
@Data
public class Jugador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private int dorsal;
	private int edad;
	
	@ManyToOne
	@JoinColumn (name="id_equipo")
	private Equipo equipo;
	
}
