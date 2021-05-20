package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ProdutoModel;

public class EditaProduto {

	private Scanner tec = new Scanner(System.in);
	private ListaProduto listaProduto;
	private ProdutoModel produto;
	private Connection connection;

	public EditaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ProdutoModel editarProduto() {
		PreparedStatement preparedStatement;
		produto = new ProdutoModel();
		listaProduto = new ListaProduto();

		int idDoProduto, indexDoCampo;

		if (listaProduto.listarProdutos() == null) {
			return null;
		}

		System.out.println("--------- EDITAR DADOS DE PRODUTOS ----------");
		System.out.println("Informe o ID do produto: ");
		idDoProduto = tec.nextInt();

		try {
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Produto não existe");
				return null;
			} else {
				produto.setNomeDoProduto(resultSet.getString("nomdeDoProduto"));
				produto.setPrecoDoProduto(resultSet.getDouble("precoDoProduto"));
				produto.setQuantidadeDeProduto(resultSet.getInt("quantidadeDeProduto"));
				produto.setNomeDoProduto(resultSet.getString("nomdeDoProduto"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		System.out.println("Informe o campo que deseja editar: ");
		System.out.println("1) Nome do produto");
		System.out.println("2) Preço unitário");
		System.out.println("3) Quantidade");
		indexDoCampo = tec.nextInt();

		switch (indexDoCampo) {
		case 1:
			editarNomeDoProduto(idDoProduto);
			break;
		case 2:
			editarPrecoDoProduto(idDoProduto);
			break;
		case 3:
			editarQuantidadeDoProduto(idDoProduto);
			break;
		default:
			System.out.println("Opção inválida!!");
			break;
		}
		return produto;
	}

	private ProdutoModel editarNomeDoProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
		System.out.println("Qual o nome do produto? ");
		produto.setNomeDoProduto(tec.next());

		try {
			String sql = "UPDATE produto SET nomdeDoProduto = ? WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, produto.getNomeDoProduto());
			preparedStatement.setInt(2, idDoProduto);

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return produto;
	}

	private ProdutoModel editarPrecoDoProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
		System.out.println("Qual o preço do produto? ");
		produto.setPrecoDoProduto(tec.nextDouble());

		produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * produto.getQuantidadeDeProduto());

		try {
			String sql = "UPDATE produto SET precoDoProduto = ?, saldoEmEstoque = ? WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setDouble(1, produto.getPrecoDoProduto());
			preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
			preparedStatement.setInt(3, idDoProduto);

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return produto;
	}

	private ProdutoModel editarQuantidadeDoProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
		System.out.println("Qual a quantidade do produto?");
		produto.setQuantidadeDeProduto(tec.nextInt());
		produto.setSaldoEmEstoque(produto.getQuantidadeDeProduto() * produto.getPrecoDoProduto());
		try {
			String sql = "UPDATE produto SET quantidadeDeProduto = ?, saldoEmEstoque = ? WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setDouble(1, produto.getQuantidadeDeProduto());
			preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
			preparedStatement.setInt(3, idDoProduto);

			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return produto;
	}

}
