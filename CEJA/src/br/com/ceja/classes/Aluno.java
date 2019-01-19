package br.com.ceja.classes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Aluno {

	private ImageIcon foto;
	private DadosPessoais dados;
	private Boletim boletim;
	private List<Disciplina> historico;
	
	public Aluno() {
		this(new ImageIcon(), new DadosPessoais(), new Boletim(), new ArrayList<Disciplina>());
	}
	
	public Aluno(ImageIcon foto, DadosPessoais dados, Boletim boletim, List<Disciplina> historico) {
		this.foto = foto;
		this.dados = dados;
		this.boletim = boletim;
		this.historico = historico;
	}

	public ImageIcon getFoto() {
		return foto;
	}

	public void setFoto(ImageIcon foto) {
		this.foto = foto;
	}

	public DadosPessoais getDados() {
		return dados;
	}

	public void setDados(DadosPessoais dados) {
		this.dados = dados;
	}

	public Boletim getBoletim() {
		return boletim;
	}

	public void setBoletim(Boletim boletim) {
		this.boletim = boletim;
	}

	public List<Disciplina> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Disciplina> historico) {
		this.historico = historico;
	}	
}
