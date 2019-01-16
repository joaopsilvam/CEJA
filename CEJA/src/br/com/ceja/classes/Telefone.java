package br.com.ceja.classes;

public class Telefone {

	private String prefixo, ddd, numero, operadora;
	
	public Telefone() {
		this("", "", "", "");
	}
	
	public Telefone(String prefixo, String ddd, String numero, String operadora) {
		this.prefixo = prefixo;
		this.ddd = ddd;
		this.numero = numero;
		this.operadora = operadora;
	}
	
	public String getPrefixo() {
		return prefixo;
	}
	
	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}
	
	public String getDdd() {
		return ddd;
	}
	
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getOperadora() {
		return operadora;
	}
	
	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}
	
	
}
