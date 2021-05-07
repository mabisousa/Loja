package br.com.senai.controller.produto;

import java.util.List;

import br.com.senai.model.ProdutoModel;

public class ListaProduto {
	public List<ProdutoModel> consultarProdutos(List<ProdutoModel> produtos) {

		if (produtos.size() <= 0) {
			System.out.println("Não há produtos para serem listados");
			return null;
		}
		System.out.println("\n----- PRODUTOS CADASTRASDOS -----\n");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$  total");
		for (int i = 0; i < produtos.size(); i++) {
			System.out.printf("| %2s | %10s | R$%6.2f | %4s | R$%7.2f |\n", i + 1, produtos.get(i).getNomeDoProduto(),
					produtos.get(i).getPrecoDoProduto(), produtos.get(i).getQuantidadeDeProduto(),
					produtos.get(i).getSaldoEmEstoque());
		}

		return produtos;
	}
}
