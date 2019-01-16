package br.com.ceja.classes;

public class Administrador {

	private String login, senha;

	public Administrador() {
		this("", "");
	}

	public Administrador(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}