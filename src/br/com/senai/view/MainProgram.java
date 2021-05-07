package br.com.senai.view;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.controller.carrinho.AdicionarItemNoCarrinho;
import br.com.senai.controller.carrinho.ListaCarrinho;
import br.com.senai.controller.cliente.AdicionaCliente;
import br.com.senai.controller.produto.CadastraProduto;
import br.com.senai.controller.produto.DeletaProduto;
import br.com.senai.controller.produto.EditaProduto;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.controller.Controller;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class MainProgram {
	public static void main(String[] args) {
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		List<CarrinhoModel> itensNoCarrinho = new ArrayList<CarrinhoModel>();
		
		Controller Controller = new Controller();
		AdicionarItemNoCarrinho adicionarCarrinho = new AdicionarItemNoCarrinho();
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		CadastraProduto cadastarProduto = new CadastraProduto();
		ListaProduto listaProduto = new ListaProduto();
		EditaProduto editaProduto = new EditaProduto();
		DeletaProduto deletaProduto = new DeletaProduto();
		AdicionaCliente adicionaCliente = new AdicionaCliente();
		
		boolean sair = false;

		String cliente = adicionaCliente.definirCliente();
		
		do {
			Controller.menu();
			int opc = Controller.opcao();

			switch (opc) {
			case 1:
				produtos.add(cadastarProduto.cadastrarProduto());
				break;
			case 2:
				listaProduto.consultarProdutos(produtos);
				break;
			case 3:
				editaProduto.editarProduto(produtos);
				break;
			case 4:
				deletaProduto.removerProdutos(produtos);
				break;
			case 5:
				itensNoCarrinho.add(adicionarCarrinho.cadastrarItemNoCarrinho(produtos));
				break;
			case 6:
				listaCarrinho.exibirCarrinho(itensNoCarrinho);
				break;
			case 7:
				listaCarrinho.gerarCupom(itensNoCarrinho, cliente);
				break;
			case 9:
				sair = true;
				break;

			default:
				System.out.println("Opção inválida!!!");
				break;
			}
		} while (!sair);

		System.out.println("Sistema encerrado!!!");
	}
}
