package br.com.ceja.exceptions;

public class AlunoNaoExisteException extends Exception{

	private static final long serialVersionUID = 1L;

	public AlunoNaoExisteException(String s) {
		super(s);
	}
}