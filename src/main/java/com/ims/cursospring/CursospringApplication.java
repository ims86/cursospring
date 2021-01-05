package com.ims.cursospring;

import com.ims.cursospring.domain.Categoria;
import com.ims.cursospring.domain.Cidade;
import com.ims.cursospring.domain.Estado;
import com.ims.cursospring.domain.Produto;
import com.ims.cursospring.repositories.CategoriaRepository;
import com.ims.cursospring.repositories.CidadeRepository;
import com.ims.cursospring.repositories.EstadoRepository;
import com.ims.cursospring.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursospringApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	CidadeRepository cidadeRepository;


	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Cria as categorias
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		//Cria os produtos
		Produto prod1 = new Produto(null, "Note", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 250.00);
		Produto prod3 = new Produto(null, "Mouse", 20.00);

		//Relaciona as categorias com os produtos
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));

		//Relaciona os produtos com as categorias
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));

		//Insere as categorias e os produtos no H2
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));

		//Cria as UFs
		Estado uf1 = new Estado(null, "SC");
		Estado uf2 = new Estado(null, "RS");

		//Cria as cidades
		Cidade cid1 = new Cidade(null, "Florianópolis", uf1);
		Cidade cid2 = new Cidade(null, "Pelotas", uf2);
		Cidade cid3 = new Cidade(null, "Capão do Leão", uf2);

		//Relaciona as cidades com as uf
		uf1.getCidades().addAll(Arrays.asList(cid1));
		uf2.getCidades().addAll(Arrays.asList(cid2, cid3));

		//Insere os Estados e as Cidades no H2
		estadoRepository.saveAll(Arrays.asList(uf1, uf2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
	}
}
