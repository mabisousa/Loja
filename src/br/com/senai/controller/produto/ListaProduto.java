package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;

public class ListaProduto {

	private Connection connection;
	
	public ListaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public ResultSet listarProdutos() {
		System.out.println("\n----- PRODUTOS CADASTRADOS -----\n");
		System.out.printf("| %2s | %15s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total");
		
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM produto;");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				System.out.printf("| %2s | %15s | %8s | %4s | %9s |\n",
					resultSet.getInt("codigoDoProduto"),
					resultSet.getString("nomdeDoProduto"),
					resultSet.getDouble("precoDoProduto"),
					resultSet.getInt("quantidadeDeProduto"),
					resultSet.getDouble("saldoEmEstoque")
					);
			}
			
			return resultSet;
		} catch (Exception e) {
			return null;
		}
		
	}
	
}