package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ProdutoModel;

public class RemoveProdutoDoCarrinho {
	private ListaCarrinho listaCarrinho = new ListaCarrinho();
	private ProdutoModel produtoModel = new ProdutoModel();
	private Scanner tec = new Scanner(System.in);
	private Connection connection;

	public RemoveProdutoDoCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public void diminuirQuantidade() {
		System.out.println("--- REMOVER PRODUTO DO CARRINHO ---");
		if (listaCarrinho.exibirCarrinho() == null) {
			System.out.println("Não há produtos no carrinho.");
			return;
		}

		System.out.println("Informe o ID do produto a ser removido");
		int idDoProduto = tec.nextInt();
		if (!verificaSeExisteProduto(idDoProduto)) {
			return;
		}

		System.out.println("Informe a quantidade desejada: ");
		int quantidade = tec.nextInt();
		if (!verificaQuantidade(idDoProduto, quantidade)) {
			return;
		}

	}

	public boolean verificaSeExisteProduto(int idDoProduto) {
		PreparedStatement preparedStatement;
		try {
			String sql = "SELECT * FROM carrinho WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				System.out.println("Esse produto não existe");
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	public boolean verificaQuantidade(int idDoProduto, int quantidade) {
		PreparedStatement preparedStatement;
		try {
			String sql = "SELECT * FROM carrinho WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getInt("quantidadeDeItensNoCarrinho") < quantidade) {
					System.out.println("Esse produto não possui essa quantidade");
				} else if (resultSet.getInt("quantidadeDeItensNoCarrinho") == quantidade) {
					removerProdutos(idDoProduto, quantidade);
				} else {
					diminuirQuantidadeDeProdutoDoCarrinho(idDoProduto, quantidade);
				}
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

	}

	public void removerProdutos(int idDoProduto, int quantidade) {
		PreparedStatement preparedStatement;
		produtoModel.setQuantidadeDeProduto(produtoModel.getQuantidadeDeProduto() + quantidade);
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDeProduto() * produtoModel.getPrecoDoProduto());

		try {
			String sql = "DELETE FROM carrinho WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			preparedStatement.execute();
			atualizaQuantidadeEValorTotal(idDoProduto);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void diminuirQuantidadeDeProdutoDoCarrinho(int idDoProduto, int quantidade) {
		PreparedStatement preparedStatement;
		produtoModel.setQuantidadeDeProduto(produtoModel.getQuantidadeDeProduto() + quantidade);
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDeProduto() * produtoModel.getPrecoDoProduto());

		try {
			String sql = "SELECT * FROM carrinho WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String sql2 = "UPDATE carrinho SET quantidadeDeItensNoCarrinho = ?, precoTotal = ? WHERE codigoDoProduto = ?";
				PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
				int newQuantidadeDoCarrinho = resultSet.getInt("quantidadeDeItensNoCarrinho") - quantidade;
				preparedStatement2.setInt(1, newQuantidadeDoCarrinho);
				preparedStatement2.setDouble(2, newQuantidadeDoCarrinho * resultSet.getDouble("precoDoProduto"));
				preparedStatement2.setInt(3, idDoProduto);
				preparedStatement2.execute();

				atualizaQuantidadeEValorTotal(idDoProduto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizaQuantidadeEValorTotal(int idDoProduto) {

		try {
			String sql = "SELECT * FROM produto WHERE codigoDoProduto = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String sql2 = "UPDATE produto SET quantidadeDeProduto = ?, saldoEmEstoque = ? WHERE codigoDoProduto = ?";
				PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
				int newQuantidade = resultSet.getInt("quantidadeDeProduto") + produtoModel.getQuantidadeDeProduto();
				preparedStatement2.setInt(1, newQuantidade);
				preparedStatement2.setDouble(2, newQuantidade * resultSet.getDouble("precoDoProduto"));
				preparedStatement2.setInt(3, idDoProduto);
				preparedStatement2.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
