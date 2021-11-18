package com.balamcode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balamcode.modelos.Jugador;

public interface JugadorRepo extends JpaRepository<Jugador, Integer>{

	List<Jugador> findAllByEquipo(int id);

}
