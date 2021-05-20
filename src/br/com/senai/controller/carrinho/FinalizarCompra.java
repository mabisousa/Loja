package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

public class FinalizarCompra {
	
	private Connection connection;

	public FinalizarCompra() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public void gerarCupom() {
		@SuppressWarnings("resource")
		Scanner tec = new Scanner(System.in);

		listarCliente();
		System.out.println("Qual cliente deseja finaliza a compra? ");
		int idDoCliente = tec.nextInt();

		somarPrecoTotal(idDoCliente);
		limpaCarrinho();

	}

	public ResultSet listarCliente() {
		PreparedStatement preparedStatement;
		try {
			String sql = "SELECT * FROM cliente";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Não possui clientes cadastrados");
				return null;
			}

			System.out.println("\n----- Clientes -----\n");
			System.out.printf("| %2s | %10s |\n", "ID", "Cliente");

			resultSet.previous();

			while (resultSet.next()) {
				System.out.printf("| %2s | %10s |\n", resultSet.getInt("id"), resultSet.getString("nome"));
			}

			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}

	public void limpaCarrinho() {
		try {
			String sql = "DELETE FROM carrinho";
			PreparedStatement preparedStatement3 = connection.prepareStatement(sql);
			preparedStatement3.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void somarPrecoTotal(int idDoCliente) {
		try {
			String sql = "SELECT nome FROM cliente WHERE ID=(?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoCliente);
			ResultSet resultSet = preparedStatement.executeQuery();
			String sql2 = "SELECT SUM(precoTotal) from carrinho";
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
			ResultSet resultSet2 = preparedStatement2.executeQuery();

			if (resultSet2.next()) {
				System.out.println("O preço total foi " + resultSet2.getDouble("SUM(precoTotal)"));
			}
			if (resultSet.next()) {
				System.out.println("Obrigado " + resultSet.getString("nome") + " por realizar sua compra");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
