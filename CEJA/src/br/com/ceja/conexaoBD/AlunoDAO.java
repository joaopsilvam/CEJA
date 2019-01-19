package br.com.ceja.conexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.com.ceja.classes.*;
import br.com.ceja.exceptions.*;

public class AlunoDAO {

	private Connection connection;
	private String[] disciplinas = {"portugues", "ingles", "espanhol", "edFisica", "arte", "historia", "geografia",
			"sociologia", "filosofia", "matematica", "fisica", "biologia", "quimica", "ciencias"};

	@SuppressWarnings("static-access")
	public AlunoDAO() {
		connection = new ConexaoMySQL().getConexaoMySQL();
	}

	public void adicionaAluno(Aluno aluno) throws AlunoJaExisteException, SQLException {

		int id = 0;
		PreparedStatement ps;

		Statement st = (Statement) connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM dadospessoais");

		while(rs.next()){
			id = rs.getInt(1);
		}

		id+=1;


		String sql = "INSERT INTO dadospessoais (id,nome,matricula,sige,dataNascimento,sexo,"
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

		sql = "INSERT INTO endereco (id,rua,bairro_distrito,cidade,cep,complemento,num) "
				+ "VALUES (?,?,?,?,?,?,?)";

		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, aluno.getDados().getEndereco().getRua());
			ps.setString(3, aluno.getDados().getEndereco().getBairro_distrito());
			ps.setString(4, aluno.getDados().getEndereco().getCidade());
			ps.setString(5, aluno.getDados().getEndereco().getCep());
			ps.setString(6, aluno.getDados().getEndereco().getComplemento());
			ps.setString(7, aluno.getDados().getEndereco().getNum());

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sql = "INSERT INTO rg (id,numero,orgao,dataExpedicao,uf) VALUES (?,?,?,?,?)";

		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, aluno.getDados().getRg().getNumero());
			ps.setString(3, aluno.getDados().getRg().getOrgao());
			ps.setString(4, aluno.getDados().getRg().getDataExpedicao());
			ps.setString(5, aluno.getDados().getRg().getUf());

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sql = "INSERT INTO telefone (id,prefixo,ddd,numero,operadora) VALUES(?,?,?,?,?)";

		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, aluno.getDados().getTelefone().getPrefixo()); 
			ps.setString(3, aluno.getDados().getTelefone().getDdd()); 
			ps.setString(4, aluno.getDados().getTelefone().getNumero()); 
			ps.setString(5, aluno.getDados().getTelefone().getOperadora()); 

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sql = "INSERT INTO certidao (id,tipo,numTermo,livro,folha,dataEmissao) VALUES(?,?,?,?,?,?)";

		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, aluno.getDados().getCertidao().getTipo()); 
			ps.setString(3, aluno.getDados().getCertidao().getNumTermo()); 
			ps.setString(4, aluno.getDados().getCertidao().getLivro()); 
			ps.setString(5, aluno.getDados().getCertidao().getFolha()); 
			ps.setString(6, aluno.getDados().getCertidao().getDataEmissao()); 

			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int quant = 0;
		st = (Statement) connection.createStatement();

		try{

			for(int k = 0; k < disciplinas.length; k++) {

				rs = st.executeQuery("SELECT quant FROM "+disciplinas[k]+" WHERE id=0");
				System.out.println(disciplinas[k]);
				while(rs.next()) quant = rs.getInt(1);
				System.out.println(quant);
				sql = "INSERT INTO "+disciplinas[k]+" (id,quant,";

				for(int i = 1; i < quant; i++) {
					sql+="a"+i+",";
				}
				sql+="a"+quant+") VALUES (?,?,";

				for(int j = 1; j < quant; j++) {
					sql+="?,";
				}
				sql+="?)";

				ps = connection.prepareStatement(sql);


				ps.setInt(1, id);
				ps.setInt(2, quant);
				for(int i = 3; i < (quant+3); i++) {
					ps.setInt(i, 0);
				}
				ps.executeUpdate();
			}
			System.out.println("deu bom");
		return;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	throw new AlunoJaExisteException("Aluno ja adicionado no sistema");
}

public void remove(String cpf) {

	PreparedStatement ps;
	int id = 0;

	String sql = "SELECT id FROM dadospessoais WHERE cpf = ?";

	try {
		ps = connection.prepareStatement(sql);
		ps.setString(1, cpf);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) id = rs.getInt("id");
		rs.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	sql = "DELETE FROM dadospessoais WHERE id = ?";

	try {
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	sql = "DELETE FROM endereco WHERE id = ?";

	try {
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	sql = "DELETE FROM rg WHERE id = ?";

	try {
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	sql = "DELETE FROM telefone WHERE id = ?";

	try {
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();	
	}

	sql = "DELETE FROM certidao WHERE id = ?";

	try {
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();	
	}

	sql = "DELETE FROM portugues WHERE id = ?";

	try {
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();	
	}

	for(String s: disciplinas) {

		sql = "DELETE FROM "+s+" WHERE id = ?";

		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
	}
}

public void altera(Aluno aluno, String cpf) throws AlunoNaoExisteException{

	PreparedStatement ps;
	int id = 0;

	String sql = "SELECT id FROM dadospessoais WHERE cpf = ?";

	try {
		ps = connection.prepareStatement(sql);
		ps.setString(1, cpf);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) id = rs.getInt("id");
		rs.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	sql = "UPDATE dadospessoais SET nome=?, matricula=?, sige=?, dataNascimento=?, sexo=?, "
			+ "naturalidade=?, cpf=?, email=?, nomePai=?, nomeMae=?, bolsaF=?, transporte=?  WHERE id=" + id;


	try {
		ps = connection.prepareStatement(sql);
		ps.setString(1, aluno.getDados().getNome());
		ps.setString(2, aluno.getDados().getMatricula());
		ps.setString(3, aluno.getDados().getSige());
		ps.setString(4, aluno.getDados().getDataNascimento());
		ps.setString(5, aluno.getDados().getSexo());
		ps.setString(6, aluno.getDados().getNaturalidade());
		ps.setString(7, aluno.getDados().getCpf());
		ps.setString(8, aluno.getDados().getEmail());
		ps.setString(9, aluno.getDados().getNomePai());
		ps.setString(10, aluno.getDados().getNomeMae());
		ps.setBoolean(11, aluno.getDados().isBolsaF());
		ps.setBoolean(12, aluno.getDados().isTransporte());

		ps.executeUpdate();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();	
	}

	sql = "UPDATE endereco SET rua=?, bairro_distrito=?, cidade=?, cep=?, complemento=?, num=? WHERE id=" + id;

	try {

		ps = connection.prepareStatement(sql);
		ps.setString(1, aluno.getDados().getEndereco().getRua());
		ps.setString(2, aluno.getDados().getEndereco().getBairro_distrito());
		ps.setString(3, aluno.getDados().getEndereco().getCidade());
		ps.setString(4, aluno.getDados().getEndereco().getCep());
		ps.setString(5, aluno.getDados().getEndereco().getComplemento());
		ps.setString(6, aluno.getDados().getEndereco().getNum());

		ps.executeUpdate();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();	
	}

	sql = "UPDATE rg SET numero=?, orgao=?, dataExpedicao=?, uf=? WHERE id=" + id;

	try {

		ps = connection.prepareStatement(sql);
		ps.setString(1, aluno.getDados().getRg().getNumero());
		ps.setString(2, aluno.getDados().getRg().getOrgao());
		ps.setString(3, aluno.getDados().getRg().getDataExpedicao());
		ps.setString(4, aluno.getDados().getRg().getUf());

		ps.executeUpdate();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	sql = "UPDATE telefone SET prefixo=?, ddd=?, numero=?, operadora=? WHERE id=" + id;

	try {

		ps = connection.prepareStatement(sql);
		ps.setString(1, aluno.getDados().getTelefone().getPrefixo());
		ps.setString(2, aluno.getDados().getTelefone().getDdd());
		ps.setString(3, aluno.getDados().getTelefone().getNumero());
		ps.setString(4, aluno.getDados().getTelefone().getOperadora());

		ps.executeUpdate();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	sql = "UPDATE certidao SET tipo=?, numTermo=?, livro=?, folha=?, dataEmissao=? WHERE id=" + id;

	try {

		ps = connection.prepareStatement(sql);
		ps.setString(1, aluno.getDados().getCertidao().getTipo()); 
		ps.setString(2, aluno.getDados().getCertidao().getNumTermo()); 
		ps.setString(3, aluno.getDados().getCertidao().getLivro()); 
		ps.setString(4, aluno.getDados().getCertidao().getFolha()); 
		ps.setString(5, aluno.getDados().getCertidao().getDataEmissao()); 

		ps.executeUpdate();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	for(String s: disciplinas) {

		int quant = 0;

		try {

			Statement st = (Statement) connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT quant FROM "+s+" WHERE id=0");

			while(rs.next()){
				quant = rs.getInt(2);
			}

			sql = "UPDATE "+s+" SET ";

			for(int i = 1; i < (quant); i++) {
				sql+="a"+i+"=?,";
			}
			sql+="a"+quant+"=? WHERE id ="+id;

			ps = connection.prepareStatement(sql);

			for(int j = 0; j < aluno.getHistorico().size(); j++) {
				for(int k = 3; k < (quant+1); k++) {
					ps.setInt(k, aluno.getHistorico().get(j).getNotas().get(k-3));

				}
				ps.executeUpdate();
			}

			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	throw new AlunoNaoExisteException("Aluno nao encontrado no banco de dados");
}

public Aluno pesquisa(String cpf) {

	PreparedStatement ps;
	ResultSet rs;
	int id = 0;

	String sql = "SELECT id FROM dadospessoais WHERE cpf = ?";

	try {
		ps = connection.prepareStatement(sql);
		ps.setString(1, cpf);
		rs = ps.executeQuery();

		if (rs.next()) id = rs.getInt("id");
		rs.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	Aluno aluno = new Aluno();
	DadosPessoais dados = new DadosPessoais();
	Endereco endereco = new Endereco();
	Rg rg = new Rg();
	Telefone tel = new Telefone();
	Certidao cert = new Certidao();

	sql = "SELECT * FROM dadospessoais WHERE id=" + id;

	try {

		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();

		if (rs.next()) {
			dados.setNome(rs.getString("nome"));
			dados.setMatricula(rs.getString("matricula"));
			dados.setSige(rs.getString("sige"));
			dados.setDataNascimento(rs.getString("dataNascimento"));
			dados.setSexo(rs.getString("sexo"));
			dados.setNaturalidade(rs.getString("naturalidade"));
			dados.setCpf(rs.getString("cpf"));
			dados.setEmail(rs.getString("email"));
			dados.setNomePai(rs.getString("nomePai"));
			dados.setNomeMae(rs.getString("nomeMae"));
			dados.setBolsaF(rs.getBoolean("bolsaF"));
			dados.setTransporte(rs.getBoolean("transporte"));
		}
		rs.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	sql = "SELECT * FROM endereco WHERE id=" + id;

	try {

		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();

		if (rs.next()) {
			endereco.setRua(rs.getString("rua"));
			endereco.setBairro_distrito(rs.getString("bairro_distrito"));
			endereco.setCidade(rs.getString("cidade"));
			endereco.setCep(rs.getString("cep"));
			endereco.setComplemento(rs.getString("complemento"));
			endereco.setNum(rs.getString("num"));
		}
		rs.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	sql = "SELECT * FROM rg WHERE id=" + id;

	try {

		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();

		if (rs.next()) {
			rg.setNumero(rs.getString("numero"));
			rg.setOrgao(rs.getString("orgao"));
			rg.setDataExpedicao(rs.getString("dataExpedicao"));
			rg.setUf(rs.getString("uf"));
		}
		rs.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	sql = "SELECT * FROM telefone WHERE id=" + id;

	try {

		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();

		if (rs.next()) {
			tel.setPrefixo(rs.getString("prefixo"));
			tel.setDdd(rs.getString("ddd"));
			tel.setNumero(rs.getString("numero"));
			tel.setOperadora(rs.getString("operadora"));
		}
		rs.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	sql = "SELECT * FROM rg WHERE id=" + id;

	try {

		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();

		if (rs.next()) {
			cert.setTipo(rs.getString("tipo"));
			cert.setNumTermo(rs.getString("numTermo"));
			cert.setLivro(rs.getString("livro"));
			cert.setFolha(rs.getString("folha"));
			cert.setDataEmissao(rs.getString("dataEmissao"));
		}
		rs.close();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	for(int i = 0; i < 14; i++) {

		sql = "SELECT * FROM "+disciplinas[i]+" WHERE id=" + id;

		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			for(int k = 0; k < aluno.getHistorico().get(i).getNotas().size(); k++) {
				if (rs.next()) {
					aluno.getHistorico().get(i).getNotas().set(k, rs.getInt(k+2));
					System.out.println(aluno.getHistorico().get(i).getNotas().set(k, rs.getInt(k+2)));
				}
			}
		
			rs.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
	}

	aluno.setDados(dados);
	aluno.getDados().setEndereco(endereco);
	aluno.getDados().setRg(rg);
	aluno.getDados().setTelefone(tel);
	aluno.getDados().setCertidao(cert);

	return aluno;
}

public List<Aluno> buscaAlunos(String nome){

	PreparedStatement ps;
	ResultSet rs;
	List<String> nomes = new ArrayList<String>();
	List<Aluno> alunos = new ArrayList<Aluno>();
	Aluno aluno;
	List<Integer> ids = new ArrayList<Integer>();
	List<DadosPessoais> dados = new ArrayList<DadosPessoais>();
	List<Endereco> enderecos = new ArrayList<Endereco>();
	List<Rg> rgs = new ArrayList<Rg>();
	List<Telefone> telefones = new ArrayList<Telefone>();
	List<Certidao> certidoes = new ArrayList<Certidao>();
	List<Integer> notas = new ArrayList<Integer>();
	List<Disciplina> disciplinas = new ArrayList<Disciplina>();

	String sql = "SELECT * FROM dadospessoais WHERE SUBSTRING(nome, 1, "+nome.length()+") = ?";

	try {
		ps = connection.prepareStatement(sql);
		ps.setString(1, nome);
		rs = ps.executeQuery();

		while(rs.next()) {
			nomes.add(rs.getString("nome"));
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	sql = "SELECT * FROM dadospessoais WHERE nome = ?";

	try {
		ps = connection.prepareStatement(sql);

		for(String s: nomes) {
			ps.setString(1, s);
			rs = ps.executeQuery();
			if(rs.next()) {
				ids.add(rs.getInt(1));
				dados.add(new DadosPessoais(
						rs.getString("nome"), 
						rs.getString("matricula"),
						rs.getString("sige"),
						rs.getString("dataNascimento"),
						rs.getString("sexo"),
						rs.getString("naturalidade"),
						rs.getString("cpf"),
						rs.getString("email"),
						rs.getString("nomePai"),
						rs.getString("nomeMae"), null, 
						rs.getBoolean("bolsaF"),
						rs.getBoolean("transporte"), 
						null, null, null));
			}
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	for(Integer i: ids) {

		sql = "SELECT * FROM endereco WHERE id = "+i;

		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				enderecos.add(new Endereco(
						rs.getString("rua"),
						rs.getString("bairro_distrito"),
						rs.getString("cidade"),
						rs.getString("cep"),
						rs.getString("complemento"),
						rs.getString("num")));
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sql = "SELECT * FROM rg WHERE id = "+i;

		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				rgs.add(new Rg(
						rs.getString("numero"),
						rs.getString("orgao"),
						rs.getString("dataExpedicao"),
						rs.getString("uf")));
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sql = "SELECT * FROM telefone WHERE id = "+i;

		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				telefones.add(new Telefone(
						rs.getString("prefixo"),
						rs.getString("ddd"),
						rs.getString("numero"),
						rs.getString("operadora")));
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sql = "SELECT * FROM certidao WHERE id = "+i;

		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				certidoes.add(new Certidao(
						rs.getString("tipo"),
						rs.getString("numTermo"),
						rs.getString("livro"),
						rs.getString("folha"),
						rs.getString("dataEmissao")));
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i1 = 0; i1 < 14; i1++) {
//
//			sql = "SELECT * FROM "+this.disciplinas[i1]+" WHERE id=" + i;
//
//			try {
//				
//				notas = new ArrayList<Integer>();
//				ps = connection.prepareStatement(sql);
//				rs = ps.executeQuery();
//				
//				for(int k = 0; k < aluno.getHistorico().get(i1).getNotas().size(); k++) {
//					if (rs.next()) {
//						aluno.getHistorico().get(i1).getNotas().set(k, rs.getInt(k+2));
//						System.out.println(aluno.getHistorico().get(i1).getNotas().set(k, rs.getInt(k+2)));
//					}
//				}
//			
//				rs.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
			}
		}
	}

	for(int k = 0; k < dados.size(); k++) {
		dados.get(k).setEndereco(enderecos.get(k));
		dados.get(k).setRg(rgs.get(k));
		dados.get(k).setTelefone(telefones.get(k));
		dados.get(k).setCertidao(certidoes.get(k));
		aluno = new Aluno();
		aluno.setDados(dados.get(k));
		alunos.add(aluno);
	}

	return alunos;
}

public static void main(String[] args) {
	//		Aluno aluno = new Aluno();
	//		aluno.getDados().setNome("Campp do Socorro Silva");
	//		aluno.getDados().setCpf("570090234-80");
	//		try {
	//			new AlunoDAO().adicionaAluno(aluno);
	//		} catch (AlunoJaExisteException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		System.out.println(new AlunoDAO().pesquisa("570090234-712").getDados().getNome());
	//		new AlunoDAO().remove("570090234-712");
	//		Aluno aluno = new Aluno();
	//		aluno.getDados().setNome("Zézin");
	//		try {
	//			new AlunoDAO().altera(aluno, ("570090234-712"));
	//		} catch (AlunoNaoExisteException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}

	AlunoDAO aluno = new AlunoDAO();

	for(Aluno d: aluno.buscaAlunos("camp")) {
		System.out.println(d.getDados().getTelefone().getNumero());
		System.out.println(d.getDados().getCpf());
		System.out.println(d.getDados().getRg().getUf());
		System.out.println(d.getDados().getEndereco().getRua());
	}
}
}

