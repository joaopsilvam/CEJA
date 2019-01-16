package br.com.ceja.classes;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {

	private String nome, turma;
	private List<Integer> notas;
	int mediaFinal;
	
	public Disciplina() {
		this("", "", new ArrayList<Integer>());
	}
	
	public Disciplina(String nome, String turma, List<Integer> notas) {
		this.nome = nome;
		this.turma = turma;
		this.notas = notas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public List<Integer> getNotas() {
		return notas;
	}

	public void setNotas(List<Integer> notas) {
		this.notas = notas;
	}

}
