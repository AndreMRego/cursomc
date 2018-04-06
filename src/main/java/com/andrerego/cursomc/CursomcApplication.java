package com.andrerego.cursomc;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andrerego.cursomc.domain.Categoria;
import com.andrerego.cursomc.domain.Produto;
import com.andrerego.cursomc.repositories.CategoriaRepository;
import com.andrerego.cursomc.repositories.ProdutoRepository;
import com.andrerego.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRespository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritŕio");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		
		cat1.getProdutos().addAll(java.util.Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(java.util.Arrays.asList(p2));
		
		p1.getCategorias().addAll(java.util.Arrays.asList(cat1));
		p2.getCategorias().addAll(java.util.Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(java.util.Arrays.asList(cat1));
		
		categoriaRespository.saveAll(java.util.Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(java.util.Arrays.asList(p1,p2,p3));
		
		
		
	}
}

