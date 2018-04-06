package com.andrerego.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrerego.cursomc.domain.Categoria;

@Repository
public interface CategoriaRespository extends JpaRepository<Categoria, Integer> {
	

}
