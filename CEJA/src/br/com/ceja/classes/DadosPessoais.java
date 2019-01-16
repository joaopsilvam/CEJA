package br.com.ceja.classes;

public class DadosPessoais {

	private String nome, matricula, sige, dataNascimento, sexo, 
	naturalidade, cpf, email, nomePai, nomeMae;
	private Rg rg;
	private boolean bolsaF, transporte;
	private Endereco endereco;
	private Telefone telefone;
	private Certidao certidao;
	
	public DadosPessoais() {
		this("", "", "", "", "", "", "", "", "", "", new Rg(), false, 
				false, new Endereco(), new Telefone(), new Certidao());
	}

	public DadosPessoais(String nome, String matricula, String sige, String dataNascimento, String sexo, String naturalidade,
			String cpf, String email, String nomePai, String nomeMae, Rg rg, boolean bolsaF, boolean transporte, 
			Endereco endereco, Telefone telefone, Certidao certidao) {
		this.nome = nome;
		this.matricula = matricula;
		this.sige = sige;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.naturalidade = naturalidade;
		this.cpf = cpf;
		this.email = email;
		this.nomePai = nomePai;
		this.nomeMae = nomeMae;
		this.rg = rg;
		this.bolsaF = bolsaF;
		this.transporte = transporte;
		this.endereco = endereco;
		this.telefone = telefone;
		this.certidao = certidao;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getSige() {
		return sige;
	}
	
	public void setSige(String sige) {
		this.sige = sige;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomePai() {
		return nomePai;
	}
	
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
	
	public String getNomeMae() {
		return nomeMae;
	}
	
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	
	public Rg getRg() {
		return rg;
	}
	
	public void setRg(Rg rg) {
		this.rg = rg;
	}
	
	public boolean isBolsaF() {
		return bolsaF;
	}
	
	public void setBolsaF(boolean bolsaF) {
		this.bolsaF = bolsaF;
	}

	public boolean isTransporte() {
		return transporte;
	}

	public void setTransporte(boolean transporte) {
		this.transporte = transporte;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	public Certidao getCertidao() {
		return certidao;
	}
	
	public void setCertidao(Certidao certidao) {
		this.certidao = certidao;
	}
}
