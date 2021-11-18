package com.balamcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.balamcode.service.IPersonaService;

@SpringBootApplication
public class DemoConsolaApplication implements CommandLineRunner{
	
	@Autowired
	private IPersonaService service;

	public static void main(String[] args) {
		SpringApplication.run(DemoConsolaApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		service.registrar("Jesusito");
	}
}
