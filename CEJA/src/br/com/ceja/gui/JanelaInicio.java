package br.com.ceja.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.ceja.classes.Administrador;
import br.com.ceja.classes.Sistema;
import br.com.ceja.exceptions.AdmJaExisteException;
import br.com.ceja.exceptions.AdmNaoExisteException;


public class JanelaInicio extends JFrame implements MouseListener, ActionListener{

	private static final long serialVersionUID = 1L;
	
	JLabel rotulo = new JLabel("");
	ImageIcon imagem = new ImageIcon(getClass().getResource("/br/com/ceja/images/layout_login2.jpg"));
	JTextField login = new JTextField("jao");
	JPasswordField senha = new JPasswordField("123");
	JButton botao = new JButton();
	Sistema sistema = new Sistema();
	
	public JanelaInicio() throws AdmJaExisteException {
		
		sistema.setJanelaPrincipal(this);
		
		sistema.addAdministrador(new Administrador("jao", "123"));
		rotulo.setBounds(0, 0, 995, 570);
		Image image = imagem.getImage().getScaledInstance(rotulo.getWidth(), rotulo.getHeight(), Image.SCALE_SMOOTH);
		rotulo.setIcon(new ImageIcon(image));

		login.setBounds(50, 239, 338, 32);
		login.setOpaque(false);
		login.setBorder(null);

		senha.setBounds(50, 303, 338, 32);
		senha.setOpaque(false);
		senha.setBorder(null);
		senha.setForeground(Color.GRAY);
		senha.addMouseListener(this);
		
		botao.setBounds(380, 303, 34, 32);
		botao.setOpaque(false);
		botao.setContentAreaFilled(false);
		botao.addActionListener(this);
		getRootPane().setDefaultButton(botao);
		
		rotulo.add(login);
		rotulo.add(senha);
		rotulo.add(botao);
		rotulo.addMouseListener(this);
		
		add(rotulo);
		new JFrame();
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(new BD().gravaDados(sistema));
		setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == senha) {
			senha.setText("");
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == botao) {
			try {
				if(sistema.logarAdministrador(login.getText(), senha.getText())) {
					setVisible(false);
					new MenuIniciar(sistema);
				}
			} catch (AdmNaoExisteException e1) {
				/** 
				 * 
				 * Colocar aqui o lançamento com janela.
				 * 
				 */
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			new JanelaInicio();
		} catch (AdmJaExisteException e) {
		}
	}
}