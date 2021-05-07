package br.com.senai.model;

import br.com.senai.model.ProdutoModel;

public class CarrinhoModel {
	private int quantidadeDeItensNoCarrinho;
	private int idDoProduto;
	private ProdutoModel produtoModel;
	private double valorTotalPorItem;
	public int getQuantidadeDeItensNoCarrinho() {
		return quantidadeDeItensNoCarrinho;
	}
	
	public CarrinhoModel() {
	}
	
	public CarrinhoModel(int quantidadeDeItensNoCarrinho, int idDoProduto, ProdutoModel produtoModel,
			double valorTotalPorItem) {
		super();
		this.quantidadeDeItensNoCarrinho = quantidadeDeItensNoCarrinho;
		this.idDoProduto = idDoProduto;
		this.produtoModel = produtoModel;
		this.valorTotalPorItem = valorTotalPorItem;
	}
	
	public void setQuantidadeDeItensNoCarrinho(int quantidadeDeItensNoCarrinho) {
		this.quantidadeDeItensNoCarrinho = quantidadeDeItensNoCarrinho;
	}
	public int getIdDoProduto() {
		return idDoProduto;
	}
	public void setIdDoProduto(int idDoProduto) {
		this.idDoProduto = idDoProduto;
	}
	public ProdutoModel getProdutoModel() {
		return produtoModel;
	}
	public void setProdutoModel(ProdutoModel produtoModel) {
		this.produtoModel = produtoModel;
	}
	public double getValorTotalPorItem() {
		return valorTotalPorItem;
	}
	public void setValorTotalPorItem(double valorTotalPorItem) {
		this.valorTotalPorItem = valorTotalPorItem;
	}
	
	
}
