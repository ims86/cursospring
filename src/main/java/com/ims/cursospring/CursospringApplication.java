package com.ims.cursospring;

import com.ims.cursospring.domain.*;
import com.ims.cursospring.domain.enums.TipoCliente;
import com.ims.cursospring.repositories.*;
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

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;


	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Moca as categorias
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		//Moca os produtos
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

		//Moca as UFs
		Estado uf1 = new Estado(null, "SC");
		Estado uf2 = new Estado(null, "RS");

		//Moca as cidades
		Cidade cid1 = new Cidade(null, "Florianópolis", uf1);
		Cidade cid2 = new Cidade(null, "Pelotas", uf2);
		Cidade cid3 = new Cidade(null, "Capão do Leão", uf2);

		//Relaciona as cidades com as uf
		uf1.getCidades().addAll(Arrays.asList(cid1));
		uf2.getCidades().addAll(Arrays.asList(cid2, cid3));

		//Insere os Estados e as Cidades no H2
		estadoRepository.saveAll(Arrays.asList(uf1, uf2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

		//Moca clientes
		Cliente cli1 = new Cliente(null, "Maria", "maria@teste.com", "123454678901", TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null, "João", "joao@teste.com", "10987654321", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll((Arrays.asList("999999999", "888888888")));
		cli2.getTelefones().addAll((Arrays.asList("777777777", "666666666")));

		//Moca enderecos
		Endereco end1 = new Endereco(null, "Rua 1", "1", "Ap1", "Teste 1", "123", cli1, cid1);
		Endereco end2 = new Endereco(null, "Rua 2", "2", "Ap2", "Teste 2", "321", cli1, cid2);
		Endereco end3 = new Endereco(null, "Rua 3", "3", "Ap3", "Teste 3", "312", cli2, cid3);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		cli2.getEnderecos().addAll(Arrays.asList(end3));

		//Insere clientes e enderecos no H2
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));

	}
}
