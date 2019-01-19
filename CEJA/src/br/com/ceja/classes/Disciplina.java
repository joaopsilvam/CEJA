package br.com.ceja.classes;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {

	private String nome;
	private List<Integer> notas;
	
	public Disciplina() {
		this("", new ArrayList<Integer>());
	}
	
	public Disciplina(String nome, List<Integer> notas) {
		this.nome = nome;
		this.notas = notas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Integer> getNotas() {
		return notas;
	}

	public void setNotas(List<Integer> notas) {
		this.notas = notas;
	}

}
