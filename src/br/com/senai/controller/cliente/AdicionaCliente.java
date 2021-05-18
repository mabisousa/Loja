package br.com.senai.controller.cliente;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ClienteModel;

public class AdicionaCliente {

	Scanner tec = new Scanner(System.in);
	private Connection connection;

	public AdicionaCliente() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ClienteModel definirCliente() {
		ClienteModel clienteModel = new ClienteModel();
		PreparedStatement preparedStatement;
		System.out.print("Informe o nome do cliente: ");
		clienteModel.setNomeDoCliente(tec.next());

		try {
			String sql = "INSERT INTO cliente VALUES ?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, clienteModel.getNomeDoCliente());

			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar os dados.");
		}

		return clienteModel;
	}
}
