package com.estudocasoumlobjetos;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estudocasoumlobjetos.domain.Categoria;
import com.estudocasoumlobjetos.domain.Cidade;
import com.estudocasoumlobjetos.domain.Cliente;
import com.estudocasoumlobjetos.domain.Endereco;
import com.estudocasoumlobjetos.domain.Estado;
import com.estudocasoumlobjetos.domain.ItemPedido;
import com.estudocasoumlobjetos.domain.Pagamento;
import com.estudocasoumlobjetos.domain.PagamentoComBoleto;
import com.estudocasoumlobjetos.domain.PagamentoComCartao;
import com.estudocasoumlobjetos.domain.Pedido;
import com.estudocasoumlobjetos.domain.Produto;
import com.estudocasoumlobjetos.domain.enums.EstadoPagamento;
import com.estudocasoumlobjetos.domain.enums.TipoCliente;
import com.estudocasoumlobjetos.repositories.CategoriaRepository;
import com.estudocasoumlobjetos.repositories.CidadeRepository;
import com.estudocasoumlobjetos.repositories.ClienteRepository;
import com.estudocasoumlobjetos.repositories.EnderecoRepository;
import com.estudocasoumlobjetos.repositories.EstadoRepository;
import com.estudocasoumlobjetos.repositories.ItemPedidoRepository;
import com.estudocasoumlobjetos.repositories.PagamentoRepository;
import com.estudocasoumlobjetos.repositories.PedidoRepository;
import com.estudocasoumlobjetos.repositories.ProdutoRepository;

@SpringBootApplication
public class EstudocasoumlobjetosApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EnderecoRepository enderecoRepositoy;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EstudocasoumlobjetosApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		Estado e1 = new Estado(null, "São Paulo");
		Estado e2 = new Estado(null, "Minas Gerais");
		
		Cidade c1 = new Cidade(null, "São Paulo", e1);
		Cidade c2 = new Cidade(null, "Campinas", e1);
		Cidade c3 = new Cidade(null, "Uberlândia", e2);
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12345678909", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("11922004488","11988776655"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c3);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		e1.getCidades().addAll(Arrays.asList(c1, c2));
		e1.getCidades().addAll(Arrays.asList(c3));
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepositoy.saveAll(Arrays.asList(end1, end2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
