package br.com.senai.controller.carrinho;

import java.util.List;

import br.com.senai.model.CarrinhoModel;

public class ListaCarrinho {

	public List<CarrinhoModel> exibirCarrinho(List<CarrinhoModel> itensNoCarrinho) {

		if (itensNoCarrinho.size() <= 0) {
			System.out.println("Não há produtos para mostrar no seu carrinho");
			return null;
		}

		System.out.println("\n----- Carrinho -----\n");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$  total");

		itensNoCarrinho.forEach(item -> {
			System.out.printf("| %2s | %10s | R$%6.2f | %4s | R$%7.2f |\n", item.getIdDoProduto(),
					item.getProdutoModel().getNomeDoProduto(), item.getProdutoModel().getPrecoDoProduto(),
					item.getQuantidadeDeItensNoCarrinho(), item.getValorTotalPorItem(), "\n");
		});
		double valorTotalDoCarrinho = itensNoCarrinho.stream().mapToDouble(item -> item.getValorTotalPorItem()).sum();
		// CarrinhoModel::getValorTotalPorItem

		System.out.println("Valor total: R$" + valorTotalDoCarrinho);

		return itensNoCarrinho;

	}
	
	public void gerarCupom(List<CarrinhoModel> itensNoCarrinho, String cliente) {
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		if(itensNoCarrinho.size() <= 0) {
			System.out.println("Lista vazia.");
			return;
		}
		listaCarrinho.exibirCarrinho(itensNoCarrinho);
		System.out.println("Cliente: " + cliente);
	}
}
