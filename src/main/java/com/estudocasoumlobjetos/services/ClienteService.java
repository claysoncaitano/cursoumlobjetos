package com.estudocasoumlobjetos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudocasoumlobjetos.domain.Cliente;
import com.estudocasoumlobjetos.exceptions.ObjectNotFoundException;
import com.estudocasoumlobjetos.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	public ClienteRepository repo;
	
	public Cliente Buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado para o Id = " + id + ", tipo = " + Cliente.class.getName()));
	}
}
