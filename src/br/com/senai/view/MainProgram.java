package br.com.senai.view;

import br.com.senai.controller.carrinho.AdicionarItemNoCarrinho;
import br.com.senai.controller.carrinho.ListaCarrinho;
import br.com.senai.controller.cliente.AdicionaCliente;
import br.com.senai.controller.produto.CadastraProduto;
import br.com.senai.controller.produto.DeletaProduto;
import br.com.senai.controller.produto.EditaProduto;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.controller.Controller;

public class MainProgram {
	public static void main(String[] args) {
		
		Controller Controller = new Controller();
		AdicionarItemNoCarrinho adicionarCarrinho = new AdicionarItemNoCarrinho();
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		CadastraProduto cadastarProduto = new CadastraProduto();
		ListaProduto listaProduto = new ListaProduto();
		EditaProduto editaProduto = new EditaProduto();
		DeletaProduto deletaProduto = new DeletaProduto();
		AdicionaCliente adicionaCliente = new AdicionaCliente();
		
		boolean sair = false;

		adicionaCliente.definirCliente();
		
		do {
			Controller.menu();
			int opc = Controller.opcao();

			switch (opc) {
			case 1:
				cadastarProduto.cadastrarProduto();
				break;
			case 2:
				listaProduto.listarProdutos();
				break;
			case 3:
				editaProduto.editarProduto();
				break;
			case 4:
				deletaProduto.removerProdutos();
				break;
			case 5:
				adicionarCarrinho.cadastrarItemNoCarrinho();
				break;
			case 6:
				listaCarrinho.exibirCarrinho();
				break;
			case 7:
				//listaCarrinho.gerarCupom();
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
