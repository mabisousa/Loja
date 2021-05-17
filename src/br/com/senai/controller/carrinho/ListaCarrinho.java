package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.CarrinhoModel;

public class ListaCarrinho {
	private Connection connection;

	public ListaCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ResultSet exibirCarrinho() {
		PreparedStatement preparedStatement;
		try {
			String sql = "SELECT * FROM carrinho;";

			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Não possui dados cadastrados");
				return null;
			}

			System.out.println("\n----- Carrinho -----\n");
			System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$  total");

			resultSet.previous();

			while (resultSet.next()) {
				System.out.printf("| %2s | %10s | R$%6.2f | %4s | R$%7.2f |\n", resultSet.getInt("codigoDoProduto"),
						resultSet.getString("nomdeDoProduto"), resultSet.getDouble("precoDoProduto"),
						resultSet.getInt("quantidadeDeItensNoCarrinho"), resultSet.getDouble("precoTotal"));
			}

			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}

	public void gerarCupom(String cliente) {
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		PreparedStatement preparedStatement;
		try {
			String sql = "SELECT * FROM carrinho;";

			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Não possui dados cadastrados");
				return;
			}
		} catch (Exception e) {
			return;
		}
		listaCarrinho.exibirCarrinho();
		System.out.println("Cliente: " + cliente);
	}
}
