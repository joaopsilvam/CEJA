package br.com.ceja.classes;

import javax.swing.ImageIcon;

public class Aluno {

	private ImageIcon foto;
	private DadosPessoais dados;
	private Boletim boletim;
	private Historico historico;
	
	public Aluno() {
		this(new ImageIcon(), new DadosPessoais(), new Boletim(), new Historico());
	}
	
	public Aluno(ImageIcon foto, DadosPessoais dados, Boletim boletim, Historico historico) {
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

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}	
}
