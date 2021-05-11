package br.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
	private static DataBaseConnection dataBaseConnection;
	private Connection connection;
	
	private DataBaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/" + 
				"loja?user=root&useSSL=false"
				);
		} catch (Exception e) {
			System.out.println("/nErro!/nN�o foi poss�vel conectar no banco de dados. Contate o suporte.");
		}
	}
	
	public static DataBaseConnection getInstance() {
		
		if(dataBaseConnection == null) dataBaseConnection = new DataBaseConnection();
		
		return dataBaseConnection;
	}
	
	public Connection getConnection() {
		return connection;
	}
}