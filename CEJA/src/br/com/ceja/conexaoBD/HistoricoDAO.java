package br.com.ceja.conexaoBD;

import java.sql.Connection;
import java.sql.SQLException;

public class HistoricoDAO {
	
	Connection connection;
	
	public HistoricoDAO() {
		connection = new ConexaoMySQL().getConexaoMySQL();
	}
	
	public void adicionaAluno(int id) {
		
		String sql = "INSERT INTO portugues (id,nome,matricula,sige,dataNascimento,sexo,"
				+ "naturalidade,cpf,email,nomePai,nomeMae,bolsaF,transporte) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {


			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, aluno.getDados().getNome());
			ps.setString(3, aluno.getDados().getMatricula());
			ps.setString(4, aluno.getDados().getSige());
			ps.setString(5, aluno.getDados().getDataNascimento());
			ps.setString(6, aluno.getDados().getSexo());
			ps.setString(7, aluno.getDados().getNaturalidade());
			ps.setString(8, aluno.getDados().getCpf());
			ps.setString(9, aluno.getDados().getEmail());
			ps.setString(10, aluno.getDados().getNomePai());
			ps.setString(11, aluno.getDados().getNomeMae());
			ps.setBoolean(12, aluno.getDados().isBolsaF());
			ps.setBoolean(13, aluno.getDados().isTransporte());

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
