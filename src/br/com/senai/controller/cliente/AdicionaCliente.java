package br.com.senai.controller.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ClienteModel;

public class AdicionaCliente {

	Scanner tec = new Scanner(System.in);
	private Connection connection;
	ClienteModel clienteModel = new ClienteModel();

	public AdicionaCliente() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ResultSet definirCliente() {

		System.out.print("Informe o nome do cliente: ");
		clienteModel.setNomeDoCliente(tec.next());
		//String nome = clienteModel.getNomeDoCliente();

		try {
			String sql = "INSERT INTO cliente (nome) VALUES (?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, clienteModel.getNomeDoCliente());

			preparedStatement.execute();
			} catch (Exception e) {
				System.out.println("Erro ao cadastrar os dados.");
			}
		
//		try {
//			String sql = "select * from cliente";
//
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);
//			ResultSet resultSet = preparedStatement.executeQuery();
//			
//			while (resultSet.next()) {
//				if (!resultSet.getString("nome").equals(nome)) {
//					cadastrarCliente(nome);
//				} else {
//					return null;
//				}
//			}
//		} catch (Exception e) {
//			System.out.println("Erro ao cadastrar os dados.");
//		}
		return null;

	}

	public void cadastrarCliente(String nome) {
		
	}
	
}
