package br.com.ceja.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;

import br.com.ceja.classes.Aluno;
import br.com.ceja.classes.Rg;
import br.com.ceja.classes.Sistema;
import br.com.ceja.exceptions.AdmJaExisteException;
import br.com.ceja.exceptions.AlunoJaExisteException;
import br.com.ceja.exceptions.AlunoNaoExisteException;

public class MenuIniciar extends JFrame implements ActionListener, MouseListener, ItemListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Sistema sistema;
	JLabel rotulo = new JLabel("");
	ImageIcon imagem = new ImageIcon(getClass().getResource("/br/com/ceja/images/menu_geral.jpg"));
	Image image;
	JButton botao1 = new JButton();
	JButton botao2 = new JButton();
	JButton botao3 = new JButton();
	JButton botao4 = new JButton();
	JButton botao5 = new JButton();
	JButton botao6 = new JButton();
	JButton botao7 = new JButton();
	JButton botao8 = new JButton();
	JTextField buscar = new JTextField("Informe o nome ou a matrícula do aluno");
	JComboBox<String> alunos = new JComboBox<String>();
	JComboBox<String> config = new JComboBox<String>();
	JList<String> lista = new JList<String>();
	List<JTextField> dados = new ArrayList<JTextField>();
	List<JRadioButton> botoes = new ArrayList<JRadioButton>();
	List<ButtonGroup> gruposBotoes = new ArrayList<ButtonGroup>();
	DefaultListModel model = new DefaultListModel();
	boolean cadastra = false;
	boolean block = false;
	Aluno aluno;

	public MenuIniciar(Sistema sistema) {

		this.sistema = sistema;

		rotulo.setBounds(0, 0, 995, 570);
		image = imagem.getImage().getScaledInstance(rotulo.getWidth(), rotulo.getHeight(), Image.SCALE_SMOOTH);
		rotulo.setIcon(new ImageIcon(image));

		botao1.setBounds(12, 32, 158, 54);
		botao1.setOpaque(false);
		botao1.setContentAreaFilled(false);
		botao1.addActionListener(this);

		botao2.setBounds(169, 32, 178, 54);
		botao2.setOpaque(false);
		botao2.setContentAreaFilled(false);
		botao2.addActionListener(this);

		botao3.setBounds(936, 45, 31, 30);
		botao3.setOpaque(false);
		botao3.setContentAreaFilled(false);
		botao3.addActionListener(this);

		botao4.setBounds(870, 176, 40, 34);
		botao4.setOpaque(false);
		botao4.setContentAreaFilled(false);
		botao4.addActionListener(this);
		botao4.setVisible(false);

		botao5.setBounds(870, 440, 89, 25);
		botao5.setOpaque(false);
		botao5.setContentAreaFilled(false);
		botao5.addActionListener(this);
		botao5.setVisible(false);

		botao6.setBounds(871, 468, 89, 24);
		botao6.setOpaque(false);
		botao6.setContentAreaFilled(false);
		botao6.addActionListener(this);
		botao6.setVisible(false);

		botao7.setBounds(918, 497, 42, 32);
		botao7.setOpaque(false);
		botao7.setContentAreaFilled(false);
		botao7.addActionListener(this);
		botao7.setVisible(false);

		botao8.setBounds(870, 497, 42, 32);
		botao8.setOpaque(false);
		botao8.setContentAreaFilled(false);
		botao8.addActionListener(this);
		botao8.setVisible(false);

		buscar.setBounds(80, 176, 795, 35);
		buscar.setOpaque(false);
		buscar.setBorder(null);
		buscar.setVisible(false);
		buscar.addMouseListener(this);

		alunos.addItemListener(this);
		alunos.setBounds(80, 210, 795, 25);
		alunos.setOpaque(false);
		alunos.setBorder(null);
		alunos.setVisible(false);
		alunos.setBackground(new Color(220, 220, 220));
		alunos.setUI(new BasicComboBoxUI() {
			protected JButton createArrowButton() {
				return new JButton() {
					@Override
					public Color getBackground() {		        		
						return super.getBackground().getColor("", new Color(220, 220, 220));
					}	
					@Override
					public Border getBorder() {
						// TODO Auto-generated method stub
						return javax.swing.BorderFactory.createLineBorder(new Color(220, 220, 220), 0);
					}
					public int getWidth() {
						return 20;
					}
				};
			}
		});

		
		lista.setBounds(877, 75, 90, 56);
		lista.setVisible(false);
		lista.setBackground(new Color(250, 177, 61));
		lista.setModel(model);
		model.addElement("Logout");
		model.addElement("Sair");
		model.addElement("About!");

		lista.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

			public void valueChanged(javax.swing.event.ListSelectionEvent evt){
				if (lista.getSelectedIndex() == 0) {
					setVisible(false);
					sistema.getJanelaPrincipal().setVisible(true);
				}				
				if (lista.getSelectedIndex() == 1) {
					System.exit(0);
				}
			}
		});
		
		addMouseListener(this);
		gerarJanelaCadastro();

		rotulo.add(botao1);
		rotulo.add(botao2);
		rotulo.add(botao3);
		rotulo.add(botao4);
		rotulo.add(botao5);
		rotulo.add(botao6);
		rotulo.add(botao7);
		rotulo.add(botao8);
		rotulo.add(buscar);
		rotulo.add(alunos);
		rotulo.add(config);
		rotulo.add(lista);

		add(rotulo);

		new JFrame();
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MenuIniciar(new Sistema());
	}

	public void gerarJanelaCadastro() {

		for(int i = 0; i < 19; i++) {
			dados.add(new JTextField(""));
			rotulo.add(dados.get(i));
			dados.get(i).setOpaque(false);
			dados.get(i).setBorder(null);

		}

		dados.get(0).setBounds(60, 161, 422, 25);
		dados.get(1).setBounds(61, 214, 192, 22);
		dados.get(2).setBounds(61, 263, 192, 22);
		dados.get(3).setBounds(61, 315, 115, 22);
		dados.get(4).setBounds(61, 360, 349, 22);
		dados.get(5).setBounds(61, 406, 349, 22);
		dados.get(6).setBounds(70, 455, 150, 22);
		dados.get(7).setBounds(61, 502, 349, 22);
		dados.get(8).setBounds(360, 214, 192, 22);
		dados.get(9).setBounds(360, 244, 192, 22);
		dados.get(10).setBounds(360, 271, 192, 22);
		dados.get(11).setBounds(360, 299, 192, 22);
		dados.get(12).setBounds(595, 164, 193, 22);
		dados.get(13).setBounds(637, 262, 291, 22);
		dados.get(14).setBounds(632, 305, 220, 22);
		dados.get(15).setBounds(872, 305, 57, 22);
		dados.get(16).setBounds(710, 351, 220, 22);
		dados.get(17).setBounds(454, 502, 151, 22);
		dados.get(18).setBounds(627, 502, 152, 22);

		for(int i = 0; i < 8; i++) {
			botoes.add(new JRadioButton());
			rotulo.add(botoes.get(i));
			botoes.get(i).setBackground(getBackground().WHITE);
		}

		botoes.get(0).setBounds(498, 164, 20, 20);
		botoes.get(1).setBounds(542, 164, 20, 20);
		botoes.get(2).setBounds(355, 198, 17, 15);
		botoes.get(3).setBounds(455, 198, 17, 15);
		botoes.get(4).setBounds(673, 417, 17, 15);
		botoes.get(5).setBounds(727, 417, 17, 15);
		botoes.get(6).setBounds(833, 417, 17, 15);
		botoes.get(7).setBounds(883, 417, 17, 15);

		alteraBotoesCaixas(false);

		for(int i = 0; i < 4; i++) {
			gruposBotoes.add(new ButtonGroup());
		}

		gruposBotoes.get(0).add(botoes.get(0));
		gruposBotoes.get(0).add(botoes.get(1));
		gruposBotoes.get(1).add(botoes.get(2));
		gruposBotoes.get(1).add(botoes.get(3));
		gruposBotoes.get(2).add(botoes.get(4));
		gruposBotoes.get(2).add(botoes.get(5));
		gruposBotoes.get(3).add(botoes.get(6));
		gruposBotoes.get(3).add(botoes.get(7));

	}

	public void alteraBotoesCaixas(boolean b) {
		botao5.setVisible(b);
		botao6.setVisible(b);
		botao7.setVisible(b);
		botao8.setVisible(b);
		for(int i = 0; i < 8; i++) {
			botoes.get(i).setVisible(b);
		}
		for(int i = 0; i < 19; i++) {
			dados.get(i).setVisible(b);
		}
	}

	public void cadastraAluno() {
		
		for(JTextField t: dados) t.setText("");
		for(ButtonGroup b: gruposBotoes) b.clearSelection();
		alunos.setVisible(false);
		cadastra = true;
		imagem = new ImageIcon(getClass().getResource("/br/com/ceja/images/cadastro.jpg"));
		image = imagem.getImage().getScaledInstance(rotulo.getWidth(), rotulo.getHeight(), Image.SCALE_SMOOTH);
		rotulo.setIcon(new ImageIcon(image));
		botao3.setBounds(935, 45, 31, 30);
		alteraBotoesCaixas(true);
		acessoDados(true);
		buscar.setVisible(false);
		botao4.setVisible(false);
		botao5.setVisible(false);
		botao6.setVisible(false);
		botao8.setVisible(false);
	}

	public void verificaAluno() {
		try {
			for(Aluno a: sistema.verificaAluno(buscar.getText())) {
				alunos.addItem(a.getDados().getNome().toUpperCase());
				alunos.setVisible(true);
			}
		} catch (AlunoNaoExisteException e1) {
			e1.printStackTrace();
		}
	}

	public void buscaAluno() {
		cadastra = false;
		imagem = new ImageIcon(getClass().getResource("/br/com/ceja/images/menu_buscar.jpg"));
		image = imagem.getImage().getScaledInstance(rotulo.getWidth(), rotulo.getHeight(), Image.SCALE_SMOOTH);
		rotulo.setIcon(new ImageIcon(image));
		botao3.setBounds(935, 45, 31, 30);
		alteraBotoesCaixas(false);
		botao5.setVisible(false);
		botao6.setVisible(false);
		botao4.setVisible(true);
		buscar.setVisible(true);
	}

	public void acessoDados(boolean bool) {
		block = false;
		for(JTextField j: dados) {
			j.setEditable(bool);
		}

		for(JRadioButton j: botoes) {
			j.setEnabled(bool);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {


		for(int i = 0; i < 4; i++) {
			if(alunos.getSelectedIndex() == i) {
				try {
					System.out.println(sistema.verificaAluno(alunos.getItemAt(i)));
				} catch (AlunoNaoExisteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource() == botao1) {
			buscar.setVisible(true);
			botao4.setVisible(true);
			buscaAluno();
			getRootPane().setDefaultButton(botao4);
		}

		if(e.getSource() == botao2) {
			cadastraAluno();
		}

		if(e.getSource() == botao3) {
			lista.setVisible(true);
		}

		if(e.getSource() == botao4) {
			alunos.removeAllItems();
			alunos.addItem("");
			verificaAluno();

		}

		if(e.getSource() == botao7) {

			
			if(cadastra) {

				try {
					Aluno aluno = new Aluno();
					aluno.getDados().setNome(dados.get(0).getText());
					aluno.getDados().setCpf(dados.get(1).getText());
					Rg rg = new Rg();
					rg.setNumero(dados.get(2).getText());
					aluno.getDados().setRg(rg);
					aluno.getDados().setDataNascimento(dados.get(3).getText());
					aluno.getDados().setNomeMae(dados.get(4).getText());
					aluno.getDados().setNomePai(dados.get(5).getText());
					aluno.getDados().getTelefone().setNumero(dados.get(6).getText());
					aluno.getDados().setEmail(dados.get(7).getText());
					aluno.getDados().getCertidao().setNumTermo(dados.get(8).getText());
					aluno.getDados().getCertidao().setFolha(dados.get(9).getText());
					aluno.getDados().getCertidao().setLivro(dados.get(10).getText());
					aluno.getDados().getCertidao().setDataEmissao(dados.get(11).getText());
					aluno.getDados().setNaturalidade(dados.get(12).getText());
					aluno.getDados().getEndereco().setRua(dados.get(13).getText());
					aluno.getDados().getEndereco().setBairro_distrito(dados.get(14).getText());
					aluno.getDados().getEndereco().setNum(dados.get(15).getText());
					aluno.getDados().getEndereco().setCidade(dados.get(16).getText());
					aluno.getDados().setMatricula(dados.get(17).getText());
					aluno.getDados().setSige(dados.get(18).getText());

					if(botoes.get(0).isSelected()) aluno.getDados().setSexo("M");
					else if(botoes.get(1).isSelected()) aluno.getDados().setSexo("F");

					if(botoes.get(2).isSelected()) aluno.getDados().getCertidao().setTipo("N");
					else if(botoes.get(3).isSelected()) aluno.getDados().getCertidao().setTipo("C");

					if(botoes.get(4).isSelected()) aluno.getDados().setTransporte(true);
					else if(botoes.get(5).isSelected()) aluno.getDados().setTransporte(false);

					if(botoes.get(6).isSelected()) aluno.getDados().setBolsaF(true);
					else if(botoes.get(7).isSelected()) aluno.getDados().setBolsaF(false);
	
					
					sistema.addAluno(aluno);
					alteraBotoesCaixas(false);
					imagem = new ImageIcon(getClass().getResource("/br/com/ceja/images/menu_geral.jpg"));
					image = imagem.getImage().getScaledInstance(rotulo.getWidth(), rotulo.getHeight(), Image.SCALE_SMOOTH);
					rotulo.setIcon(new ImageIcon(image));
					for(JTextField t: dados) t.setText("");
					/**
					 * 
					 * 
					 * Colocar janela de aluno cadastrado.
					 */
				} catch (AlunoJaExisteException e1) {
					e1.printStackTrace();
				}
			}else if(!block){
				botao5.setVisible(false);
				botao6.setVisible(false);
				acessoDados(true);
				block = true;
				imagem = new ImageIcon(getClass().getResource("/br/com/ceja/images/alterar_dados.jpg"));
				image = imagem.getImage().getScaledInstance(rotulo.getWidth(), rotulo.getHeight(), Image.SCALE_SMOOTH);
				rotulo.setIcon(new ImageIcon(image));
			}else if(block) {
				Aluno aluno = new Aluno();
				aluno.getDados().setNome(dados.get(0).getText());
				aluno.getDados().setCpf(dados.get(1).getText());
				Rg rg = new Rg();
				rg.setNumero(dados.get(2).getText());
				aluno.getDados().setRg(rg);
				aluno.getDados().setDataNascimento(dados.get(3).getText());
				aluno.getDados().setNomeMae(dados.get(4).getText());
				aluno.getDados().setNomePai(dados.get(5).getText());
				aluno.getDados().getTelefone().setNumero(dados.get(6).getText());
				aluno.getDados().setEmail(dados.get(7).getText());
				aluno.getDados().getCertidao().setNumTermo(dados.get(8).getText());
				aluno.getDados().getCertidao().setFolha(dados.get(9).getText());
				aluno.getDados().getCertidao().setLivro(dados.get(10).getText());
				aluno.getDados().getCertidao().setDataEmissao(dados.get(11).getText());
				aluno.getDados().setNaturalidade(dados.get(12).getText());
				aluno.getDados().getEndereco().setRua(dados.get(13).getText());
				aluno.getDados().getEndereco().setBairro_distrito(dados.get(14).getText());
				aluno.getDados().getEndereco().setNum(dados.get(15).getText());
				aluno.getDados().getEndereco().setCidade(dados.get(16).getText());
				aluno.getDados().setMatricula(dados.get(17).getText());
				aluno.getDados().setSige(dados.get(18).getText());

				if(botoes.get(0).isSelected()) aluno.getDados().setSexo("M");
				else if(botoes.get(1).isSelected()) aluno.getDados().setSexo("F");

				if(botoes.get(2).isSelected()) aluno.getDados().getCertidao().setTipo("N");
				else if(botoes.get(3).isSelected()) aluno.getDados().getCertidao().setTipo("C");

				if(botoes.get(4).isSelected()) aluno.getDados().setTransporte(true);
				else if(botoes.get(5).isSelected()) aluno.getDados().setTransporte(false);

				if(botoes.get(6).isSelected()) aluno.getDados().setBolsaF(true);
				else if(botoes.get(7).isSelected()) aluno.getDados().setBolsaF(false);

				sistema.alteraAluno(aluno, this.aluno.getDados().getCpf());
				
				botao5.setVisible(true);
				botao6.setVisible(true);
				acessoDados(false);
				imagem = new ImageIcon(getClass().getResource("/br/com/ceja/images/dados.jpg"));
				image = imagem.getImage().getScaledInstance(rotulo.getWidth(), rotulo.getHeight(), Image.SCALE_SMOOTH);
				rotulo.setIcon(new ImageIcon(image));
			}
		}
		
		if(e.getSource() == botao8) {
			alteraBotoesCaixas(false);
			System.out.println(dados.get(1).getText());
			sistema.removeAluno(dados.get(1).getText());
			imagem = new ImageIcon(getClass().getResource("/br/com/ceja/images/menu_geral.jpg"));
			image = imagem.getImage().getScaledInstance(rotulo.getWidth(), rotulo.getHeight(), Image.SCALE_SMOOTH);
			rotulo.setIcon(new ImageIcon(image));
		}


	}

	public void mouseClicked(MouseEvent e) {
		lista.setVisible(false);
		alunos.setVisible(false);
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == buscar) {
			buscar.setText("");
		}
	}
	public void mouseReleased(MouseEvent e) {		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		for(int i = 1; i < alunos.getItemCount(); i++) {
			if(alunos.getSelectedIndex() == i) {
				aluno = sistema.getAluno(alunos.getItemAt(i));
				dados.get(0).setText(aluno.getDados().getNome());
				dados.get(1).setText(aluno.getDados().getCpf());
				dados.get(2).setText(aluno.getDados().getRg().getNumero());
				dados.get(3).setText(aluno.getDados().getDataNascimento());
				dados.get(4).setText(aluno.getDados().getNomeMae());
				dados.get(5).setText(aluno.getDados().getNomePai());
				dados.get(6).setText(aluno.getDados().getTelefone().getNumero());
				dados.get(7).setText(aluno.getDados().getEmail());
				dados.get(8).setText(aluno.getDados().getCertidao().getNumTermo());
				dados.get(9).setText(aluno.getDados().getCertidao().getFolha());
				dados.get(10).setText(aluno.getDados().getCertidao().getLivro());
				dados.get(11).setText(aluno.getDados().getCertidao().getDataEmissao());
				dados.get(12).setText(aluno.getDados().getNaturalidade());
				dados.get(13).setText(aluno.getDados().getEndereco().getRua());
				dados.get(14).setText(aluno.getDados().getEndereco().getBairro_distrito());
				dados.get(15).setText(aluno.getDados().getEndereco().getNum());
				dados.get(16).setText(aluno.getDados().getEndereco().getCidade());
				dados.get(17).setText(aluno.getDados().getMatricula());
				dados.get(18).setText(aluno.getDados().getSige());


				if(aluno.getDados().getSexo().equals("M")) botoes.get(0).setSelected(true);
				else if(aluno.getDados().getSexo().equals("F")) botoes.get(1).setSelected(true);
				
				if(aluno.getDados().getCertidao().getTipo().equals("N")) botoes.get(2).setSelected(true);
				else if(aluno.getDados().getCertidao().getTipo().equals("C")) botoes.get(3).setSelected(true);

				if(aluno.getDados().isTransporte()) botoes.get(4).setSelected(true);
				else if(!aluno.getDados().isTransporte()) botoes.get(5).setSelected(true);

				if(aluno.getDados().isBolsaF()) botoes.get(6).setSelected(true);
				else if(!aluno.getDados().isBolsaF()) botoes.get(7).setSelected(true);
				
				botao4.setVisible(false);
				buscar.setVisible(false);
				alunos.setVisible(false);
				acessoDados(false);
				alteraBotoesCaixas(true);
				imagem = new ImageIcon(getClass().getResource("/br/com/ceja/images/dados.jpg"));
				image = imagem.getImage().getScaledInstance(rotulo.getWidth(), rotulo.getHeight(), Image.SCALE_SMOOTH);
				rotulo.setIcon(new ImageIcon(image));
			}

		}
	}
}