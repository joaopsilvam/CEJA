package br.com.ceja.classes;

public class Endereco {

	private String rua, bairro_distrito, cidade, cep, complemento, num;
	
	public Endereco() {
		this("", "", "", "", "", "");
	}
	
	public Endereco(String rua, String bairro_distrito, String cidade, String cep, String complemento, String num) {
		this.rua = rua;
		this.bairro_distrito = bairro_distrito;
		this.cidade = cidade;
		this.cep = cep;
		this.complemento = complemento;
		this.num = num;
	}
	
	public String getRua() {
		return rua;
	}
	
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public String getBairro_distrito() {
		return bairro_distrito;
	}
	
	public void setBairro_distrito(String bairro_distrito) {
		this.bairro_distrito = bairro_distrito;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getNum() {
		return num;
	}
	
	public void setNum(String num) {
		this.num = num;
	}
}
