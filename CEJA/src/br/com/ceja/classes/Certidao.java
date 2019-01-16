package br.com.ceja.classes;

public class Certidao {

	private String tipo, numTermo, livro, folha, dataEmissao;

	public Certidao() {
		this("", "", "", "", "");
	}
	
	public Certidao(String tipo, String numTermo, String livro, String folha, String dataEmissao) {
		this.tipo = tipo;
		this.numTermo = numTermo;
		this.livro = livro;
		this.folha = folha;
		this.dataEmissao = dataEmissao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumTermo() {
		return numTermo;
	}

	public void setNumTermo(String numTermo) {
		this.numTermo = numTermo;
	}

	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro = livro;
	}

	public String getFolha() {
		return folha;
	}

	public void setFolha(String folha) {
		this.folha = folha;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}	
}
