package com.estudocasoumlobjetos.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estudocasoumlobjetos.domain.Pedido;
import com.estudocasoumlobjetos.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		//Pedido cat1 = new Pedido(1, "Roupa");
		//Pedido cat2 = new Pedido(2, "Calçado");
		
		//List<Pedido> categorias = new ArrayList<>();
		//categorias.add(cat1);
		//categorias.add(cat2);
		//return categorias;
		
		Pedido obj = service.Buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}
