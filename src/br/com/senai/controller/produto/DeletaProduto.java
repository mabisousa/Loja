package br.com.senai.controller.produto;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

public class DeletaProduto {
	ListaProduto listaProduto = new ListaProduto();
	Scanner tec = new Scanner(System.in);
	public void removerProdutos(List<ProdutoModel> produtos) {
		System.out.println("--- REMOVER PRODUTO ---");
		if (produtos.size() <= 0) {
			System.out.println("Não há produtos cadastrados");
			return;
		}

		listaProduto.listarProdutos();


		System.out.println("Informe o ID do produto  ser removido");
		int id = tec.nextInt();

		if (id >= produtos.size()) {
			System.out.println("Produto não cadastrado");
			return;
		}
		produtos.remove(id - 1);
	}
}
