package br.com.senai.controller.carrinho;

import java.util.List;
import java.util.Scanner;

import br.com.senai.controller.produto.EditaProduto;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class AdicionarItemNoCarrinho {
	Scanner tec = new Scanner(System.in);
	ListaProduto listaProduto = new ListaProduto();
	EditaProduto editaProduto = new EditaProduto();
	public CarrinhoModel cadastrarItemNoCarrinho(List<ProdutoModel> produtos) {
		CarrinhoModel carrinho = new CarrinhoModel();
		
		if (produtos.size() <= 0) {
			System.out.println("Não há produtos cadastrados");
			return null;
		}
		
		listaProduto.listarProdutos();

		System.out.println("----- CARRINHO -----");

		System.out.println("Informe o ID do produto: ");
		carrinho.setIdDoProduto(tec.nextInt());
		int idDoProduto = carrinho.getIdDoProduto() - 1;

		if (carrinho.getIdDoProduto()-1 >= produtos.size()) {
			System.out.println("Produto não cadastrado");
			return null;
		}

		System.out.println("Informe a quantidade desejada: ");
		carrinho.setQuantidadeDeItensNoCarrinho(tec.nextInt());

		if (carrinho.getQuantidadeDeItensNoCarrinho() > produtos.get(idDoProduto).getQuantidadeDeProduto()) {
			System.out.println("Esse produto não possui toda essa quantidade");
			return null;
		}
		editaProduto.atualizaQuantidadeEValorTotal(produtos, carrinho.getQuantidadeDeItensNoCarrinho(), idDoProduto);
		
		carrinho.setProdutoModel(produtos.get(idDoProduto));
		carrinho.setValorTotalPorItem(
				carrinho.getQuantidadeDeItensNoCarrinho() * produtos.get(idDoProduto).getPrecoDoProduto());

		return carrinho;
	}
}
