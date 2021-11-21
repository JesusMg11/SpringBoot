package com.balamcode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balamcode.model.Persona;

public interface IPersonaRepo extends JpaRepository<Persona, Integer>{

	
}
