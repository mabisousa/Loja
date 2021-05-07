package br.com.senai.model;

public class ProdutoModel {

	// Atributos
	private String nomdeDoProduto;
	private double precoDoProduto;
	private int quantidadeDeProduto;
	private double saldoEmEstoque;
	// CONSTRUCTORS
	public ProdutoModel() {
	}

	public ProdutoModel(String nomdeDoProduto, double precoDoProduto, int quantidadeDeProduto, double saldoEmEstoque) {
		super();
		this.nomdeDoProduto = nomdeDoProduto;
		this.precoDoProduto = precoDoProduto;
		this.quantidadeDeProduto = quantidadeDeProduto;
		this.saldoEmEstoque = saldoEmEstoque;
	}

	// METODOS ACE/MOD
	public String getNomeDoProduto() {
		return nomdeDoProduto;
	}

	public void setNomeDoProduto(String nomdeDoProduto) {
		this.nomdeDoProduto = nomdeDoProduto;
	}

	public double getPrecoDoProduto() {
		return precoDoProduto;
	}

	public void setPrecoDoProduto(double precoDoProduto) {
		this.precoDoProduto = precoDoProduto;
	}

	public int getQuantidadeDeProduto() {
		return quantidadeDeProduto;
	}

	public void setQuantidadeDeProduto(int quantidadeDeProduto) {
		this.quantidadeDeProduto = quantidadeDeProduto;
	}

	public double getSaldoEmEstoque() {
		return saldoEmEstoque;
	}

	public void setSaldoEmEstoque(double saldoEmEstoque) {
		this.saldoEmEstoque = saldoEmEstoque;
	}

	@Override
	public String toString() {
		return "Nome do produto: " + nomdeDoProduto + "\nPreço do Produto: " + precoDoProduto
				+ "\nQuantidade De Produto: " + quantidadeDeProduto + "\nSaldo Em Estoque: " + saldoEmEstoque;
	}
	
	
}
