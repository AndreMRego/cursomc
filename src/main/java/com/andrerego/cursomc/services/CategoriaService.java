package com.andrerego.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrerego.cursomc.domain.Categoria;
import com.andrerego.cursomc.repositories.CategoriaRespository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRespository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
}
