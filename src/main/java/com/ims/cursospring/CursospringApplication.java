package com.ims.cursospring;

import com.ims.cursospring.domain.*;
import com.ims.cursospring.domain.enums.StatusPagamento;
import com.ims.cursospring.domain.enums.TipoCliente;
import com.ims.cursospring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
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

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	PagamentoRepository pagamentoRepository;

	@Autowired
	PedidoItemRepository pedidoItemRepository;


	public static void main(String[] args) {
		SpringApplication.run(CursospringApplication.class, args);
	}

	//Mock dados para testes
	@Override
	public void run(String... args) throws Exception {

		//Mock categorias
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Eletrônicos");
		Categoria cat4 = new Categoria(null, "Periféricos");
		Categoria cat5 = new Categoria(null, "Ferramentas");
		Categoria cat6 = new Categoria(null, "Áudio");
		Categoria cat7 = new Categoria(null, "Video");
		Categoria cat8 = new Categoria(null, "Cabos");
		Categoria cat9 = new Categoria(null, "Conectores");
		Categoria cat10 = new Categoria(null, "Cadeiras");
		Categoria cat11 = new Categoria(null, "Mesas");
		Categoria cat12 = new Categoria(null, "Prateleiras");


		//Mock produtos
		Produto prod1 = new Produto(null, "Note", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 550.00);
		Produto prod3 = new Produto(null, "Mouse", 20.00);
		Produto prod4 = new Produto(null, "Teclado", 40.00);
		Produto prod5 = new Produto(null, "Cabo RJ45", 1.00);
		Produto prod6 = new Produto(null, "LED", 1.00);
		Produto prod7 = new Produto(null, "Cageira", 300.00);
		Produto prod8 = new Produto(null, "Mesa escritório", 450.00);
		Produto prod9 = new Produto(null, "Conector RJ45", 2.00);
		Produto prod10 = new Produto(null, "Prateleira 40", 30.00);
		Produto prod11 = new Produto(null, "Prateleira 60", 40.00);
		Produto prod12 = new Produto(null, "Prateleira 100", 60.00);

		//Relaciona as categorias com os produtos
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod9));
		cat2.getProdutos().addAll(Arrays.asList(prod2, prod7, prod8, prod10, prod11, prod12));
		cat3.getProdutos().addAll(Arrays.asList(prod6));
		cat4.getProdutos().addAll(Arrays.asList(prod3, prod4));
		cat8.getProdutos().addAll(Arrays.asList(prod5));
		cat9.getProdutos().addAll(Arrays.asList(prod9));
		cat10.getProdutos().addAll(Arrays.asList(prod7));
		cat11.getProdutos().addAll(Arrays.asList(prod8));
		cat12.getProdutos().addAll(Arrays.asList(prod10, prod11, prod12));


		//Relaciona os produtos com as categorias
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		prod4.getCategorias().addAll(Arrays.asList(cat1));
		prod5.getCategorias().addAll(Arrays.asList(cat1, cat8));
		prod6.getCategorias().addAll(Arrays.asList(cat3));
		prod7.getCategorias().addAll(Arrays.asList(cat2, cat10));
		prod8.getCategorias().addAll(Arrays.asList(cat2, cat11));
		prod9.getCategorias().addAll(Arrays.asList(cat1, cat9));
		prod10.getCategorias().addAll(Arrays.asList(cat2, cat12));
		prod11.getCategorias().addAll(Arrays.asList(cat2, cat12));
		prod12.getCategorias().addAll(Arrays.asList(cat2, cat12));

		//Insere as categorias e os produtos no H2
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11, prod12));

		//Mock UFs
		Estado uf1 = new Estado(null, "SC");
		Estado uf2 = new Estado(null, "RS");

		//Mock cidades
		Cidade cid1 = new Cidade(null, "Florianópolis", uf1);
		Cidade cid2 = new Cidade(null, "Pelotas", uf2);
		Cidade cid3 = new Cidade(null, "Capão do Leão", uf2);

		//Relaciona as cidades com as uf
		uf1.getCidades().addAll(Arrays.asList(cid1));
		uf2.getCidades().addAll(Arrays.asList(cid2, cid3));

		//Insere os Estados e as Cidades no H2
		estadoRepository.saveAll(Arrays.asList(uf1, uf2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

		//Mock clientes
		Cliente cli1 = new Cliente(null, "Maria", "maria@teste.com", "123454678901", TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null, "João", "joao@teste.com", "10987654321", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll((Arrays.asList("999999999", "888888888")));
		cli2.getTelefones().addAll((Arrays.asList("777777777", "666666666")));

		//Mock enderecos
		Endereco end1 = new Endereco(null, "Rua 1", "1", "Ap1", "Teste 1", "123", cli1, cid1);
		Endereco end2 = new Endereco(null, "Rua 2", "2", "Ap2", "Teste 2", "321", cli1, cid2);
		Endereco end3 = new Endereco(null, "Rua 3", "3", "Ap3", "Teste 3", "312", cli2, cid3);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		cli2.getEnderecos().addAll(Arrays.asList(end3));

		//Insere clientes e enderecos no H2
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3));

		//Mock pedidos e pagamentos
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/Mm/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, dateFormat.parse("30/12/2020 10:00"), cli1, end1);
		Pedido ped2 = new Pedido(null, dateFormat.parse("31/12/2020 14:00"), cli1, end2);

		Pagamento pagt1 = new PagamentoCartao(null, StatusPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagt1);
		Pagamento pagt2 = new PagamentoBoleto(null, StatusPagamento.PENDENTE, ped2, dateFormat.parse("31/12/2020 14:00"), null);
		ped2.setPagamento(pagt2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagt1, pagt2));

		//Mock pedidoItem
		PedidoItem pi1 = new PedidoItem(ped1, prod1, 0.00, 1, 2000.00);
		PedidoItem pi2 = new PedidoItem(ped1, prod3, 50.00, 1, 20.00);
		PedidoItem pi3 = new PedidoItem(ped2, prod2, 100.00, 1, 250.00);

		ped1.getPedidoItens().addAll(Arrays.asList(pi1, pi2));
		ped2.getPedidoItens().addAll(Arrays.asList(pi3));

		prod1.getPedidoItens().addAll(Arrays.asList(pi1));
		prod2.getPedidoItens().addAll(Arrays.asList(pi3));
		prod3.getPedidoItens().addAll(Arrays.asList(pi2));

		pedidoItemRepository.saveAll(Arrays.asList(pi1, pi2, pi3));

	}
}
