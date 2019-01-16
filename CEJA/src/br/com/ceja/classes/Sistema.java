package br.com.ceja.classes;

import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import br.com.ceja.conexaoBD.AlunoDAO;
import br.com.ceja.exceptions.*;

public class Sistema {

	private AlunoDAO dao;
	private List<Aluno> alunos;
	private List<Administrador> adms;
	public JFrame janelaPrincipal;
	
	public Sistema() {
		this(new ArrayList<Aluno>(), new ArrayList<Administrador>());
	}
	
	public Sistema(List<Aluno> alunos, List<Administrador> adms) {
		dao = new AlunoDAO();
		this.alunos = alunos;
		this.adms = adms;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Administrador> getAdministradores() {
		return adms;
	}

	public void setAdministradores(List<Administrador> adms) {
		this.adms = adms;
	}
	
	public JFrame getJanelaPrincipal() {
		return janelaPrincipal;
	}

	public void setJanelaPrincipal(JFrame janelaPrincipal) {
		this.janelaPrincipal = janelaPrincipal;
	}
	
	public void addAluno(Aluno aluno) throws AlunoJaExisteException{
		
		try {
			dao.adicionaAluno(aluno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeAluno(String cpf) {
		dao.remove(cpf);
	}
	
//	public static String removerAcentos(String str) {
//	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
//	}
	
	public List<Aluno> verificaAluno(String identificacao) throws AlunoNaoExisteException{
//		for(Aluno a: this.alunos) {
//			if(removerAcentos(a.getDados().getNome()).toUpperCase().startsWith(removerAcentos(identificacao).toUpperCase()) || a.getDados().getMatricula().startsWith(identificacao)) {
//				alunos.add(a);
//			}
//		}
//		if(alunos.isEmpty()) throw new AlunoNaoExisteException("O aluno não foi encontrado no sistema.");
		
		return dao.buscaAlunos(identificacao);
	}
	
	public void alteraAluno(Aluno aluno, String cpf) {
		try {
			dao.altera(aluno, cpf);
		} catch (AlunoNaoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addAdministrador(Administrador adm) throws AdmJaExisteException{
		for(Administrador a: adms) {
			if(a.getLogin().equals(adm.getLogin())) {
				throw new AdmJaExisteException("Esse usuário já foi cadastrado no sistema.");
			}
		}
		adms.add(adm);
	}
	
	public void removeAdministrador(Administrador adm) {
		adms.remove(adm);
	}
	
	public Administrador verificaAdministrador(String login) throws AdmNaoExisteException{
		for(Administrador a: adms) {
			if(a.getLogin().equals(login)) {
				return a;
			}
		}
		throw new AdmNaoExisteException("O usuário não foi encontrado no sistema.");	
	}
	
	public boolean logarAdministrador(String login, String senha) throws AdmNaoExisteException{
		for(Administrador a: adms) {
			if(a.getLogin().equals(login) && a.getSenha().equals(senha)) {
				return true;
			}
		}
		throw new AdmNaoExisteException("O usuário não foi encontrado no sistema.");	
	}
	
	public void exitSistem() {
		System.exit(0);
	}
	
	public Aluno getAluno(String nome) {
		return dao.buscaAlunos(nome).get(0);
	}
	
	public static String removeAccents(String str) {
	    str = Normalizer.normalize(str, Normalizer.Form.NFD);
	    str = str.replaceAll("[^\\p{ASCII}]", "");
	    return str;
	}
}