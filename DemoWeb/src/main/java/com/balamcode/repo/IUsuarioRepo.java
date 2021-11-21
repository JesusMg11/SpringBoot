package com.balamcode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balamcode.model.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer>{

	//findBy es el equivalente a where, donde concatenamos el valor que queremos buscar
	//adicional al parametro de busqueda
	Usuario findByNombre(String nombre);
}
