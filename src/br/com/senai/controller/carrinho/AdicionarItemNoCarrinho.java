package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class AdicionarItemNoCarrinho {
	private Scanner tec = new Scanner(System.in);
	private ListaProduto listaProduto = new ListaProduto();
	private ProdutoModel produtoModel = new ProdutoModel();
	private Connection connection;

	public AdicionarItemNoCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ResultSet CadastrarItemNoCarrinho() {
		CarrinhoModel carrinho = new CarrinhoModel();
		PreparedStatement preparedStatement;

		System.out.println("----- CARRINHO -----");
		listaProduto.listarProdutos();

		System.out.println("Informe o ID do produto: ");
		int idDoProduto = tec.nextInt();
		carrinho.setIdDoProduto(idDoProduto);
		System.out.println("Informe a quantidade desejada: ");
		int quantidade = tec.nextInt();
		produtoModel.setQuantidadeDeProduto(quantidade);

		try {
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				System.out.println("Este produto não existe");
				return null;
			} else {
				produtoModel.setNomeDoProduto(resultSet.getString("nomdeDoProduto"));
				produtoModel.setPrecoDoProduto(resultSet.getDouble("precoDoProduto"));
				carrinho.setValorTotalPorItem(quantidade * produtoModel.getPrecoDoProduto());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		try {
			String sql = "INSERT INTO carrinho (codigoDoProduto, nomdeDoProduto, precoDoProduto, precoTotal, quantidadeDeItensNoCarrinho) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql);

			preparedStatement2.setInt(1, carrinho.getIdDoProduto());
			preparedStatement2.setString(2, produtoModel.getNomeDoProduto());
			preparedStatement2.setDouble(3, produtoModel.getPrecoDoProduto());
			preparedStatement2.setDouble(4, carrinho.getValorTotalPorItem());
			preparedStatement2.setInt(5, produtoModel.getQuantidadeDeProduto());

			preparedStatement2.execute();

			atualizaQuantidadeEValorTotal(idDoProduto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void atualizaQuantidadeEValorTotal(int idDoProduto) {
		try {
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String sql2 = "UPDATE produto SET quantidadeDeProduto = ? WHERE codigoDoProduto = ?";
				PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
				preparedStatement2.setInt(1,
						resultSet.getInt("quantidadeDeProduto") - produtoModel.getQuantidadeDeProduto());
				preparedStatement2.setInt(2, idDoProduto);
				preparedStatement2.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
