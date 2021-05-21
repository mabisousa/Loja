package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ProdutoModel;

public class CadastraProduto {
	Scanner tec = new Scanner(System.in);

	private Connection connection;

	public CadastraProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ProdutoModel cadastrarProduto() {
		ProdutoModel produtoModel = new ProdutoModel();

		System.out.println("\n--- CADASTRAR ITENS ---\n");
		System.out.print("Produto: ");
		produtoModel.setNomeDoProduto(tec.next());
		System.out.print("Preço: ");
		produtoModel.setPrecoDoProduto(tec.nextDouble());
		System.out.print("Quantidade:");
		produtoModel.setQuantidadeDeProduto(tec.nextInt());
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDeProduto() * produtoModel.getPrecoDoProduto());

		try {
			String sql = "INSERT INTO produto (nomdeDoProduto, precoDoProduto, quantidadeDeProduto, saldoEmEstoque) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, produtoModel.getNomeDoProduto());
			preparedStatement.setDouble(2, produtoModel.getPrecoDoProduto());
			preparedStatement.setInt(3, produtoModel.getQuantidadeDeProduto());
			preparedStatement.setDouble(4, produtoModel.getSaldoEmEstoque());

			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar os dados.");
		}

		return produtoModel;
	}
}
