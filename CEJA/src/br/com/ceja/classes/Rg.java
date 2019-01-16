package br.com.ceja.classes;

public class Rg {

	private String numero, orgao, dataExpedicao, uf;

	public Rg() {
		this("", "", "", "");
	}
	
	public Rg(String numero, String orgao, String dataExpedicao, String uf) {
		this.numero = numero;
		this.orgao = orgao;
		this.dataExpedicao = dataExpedicao;
		this.uf = uf;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getOrgao() {
		return orgao;
	}

	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}

	public String getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(String dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
