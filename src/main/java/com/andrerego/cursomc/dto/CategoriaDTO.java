package com.andrerego.cursomc.dto;

import java.io.Serializable;

import com.andrerego.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public CategoriaDTO() {
		
	}
	
	//responsavel por pegar a lista de categorias e trazer para o DTO
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome=obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
