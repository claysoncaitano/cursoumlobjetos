package com.estudocasoumlobjetos.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estudocasoumlobjetos.domain.Cliente;
import com.estudocasoumlobjetos.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		//Cliente cat1 = new Cliente(1, "Roupa");
		//Cliente cat2 = new Cliente(2, "Calçado");
		
		//List<Cliente> categorias = new ArrayList<>();
		//categorias.add(cat1);
		//categorias.add(cat2);
		//return categorias;
		
		Cliente obj = service.Buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}
