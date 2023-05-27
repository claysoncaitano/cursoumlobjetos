package com.estudocasoumlobjetos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudocasoumlobjetos.domain.Categoria;
import com.estudocasoumlobjetos.exceptions.ObjectNotFoundException;
import com.estudocasoumlobjetos.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository repo;
	
	public Categoria Buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado para o Id = " + id + ", tipo = " + Categoria.class.getName()));
	}
}
