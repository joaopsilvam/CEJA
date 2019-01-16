package br.com.ceja.exceptions;

public class AdmNaoExisteException extends Exception{

	private static final long serialVersionUID = 1L;

	public AdmNaoExisteException(String s) {
		super(s);
	}
}
