package br.com.senai.controller;

import java.util.Scanner;


public class Controller {

	private Scanner tec;
	
	public Controller() {
		tec = new Scanner(System.in);
	}

	public int opcao() {
		System.out.print("> ");
		return tec.nextInt();
	}
	

	public void menu() {
		System.out.println("\n--- MENU ---\n");
		System.out.println("1) Cadastrar itens");
		System.out.println("2) Listar estoque");
		System.out.println("3) Editar item");
		System.out.println("4) Remover item");
		System.out.println("5) Adicionar no Carrinho");
		System.out.println("6) Exibir carrinho");
		System.out.println("7) Gerar cupom");
		System.out.println("8) Cadastrar cliente");
		System.out.println("9) Listar cliente");
		System.out.println("10) Sair do sistema");
		System.out.println("--------------------");
	}

}
