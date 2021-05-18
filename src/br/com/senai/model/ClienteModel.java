package br.com.senai.model;

public class ClienteModel {
	private String nome;
	
	public ClienteModel() {
	}

	public ClienteModel(String nome) {
		super();
		this.nome = nome;
	}

	// METODOS ACE/MOD
	public String getNomeDoCliente() {
		return nome;
	}

	public void setNomeDoCliente(String nome) {
		this.nome = nome;
	}

}
