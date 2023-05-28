package com.estudocasoumlobjetos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudocasoumlobjetos.domain.Pedido;
import com.estudocasoumlobjetos.exceptions.ObjectNotFoundException;
import com.estudocasoumlobjetos.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	public PedidoRepository repo;
	
	public Pedido Buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado para o Id = " + id + ", tipo = " + Pedido.class.getName()));
	}
}
