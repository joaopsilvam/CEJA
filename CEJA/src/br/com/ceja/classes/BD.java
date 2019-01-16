package br.com.ceja.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BD {	

	File file;
	
	public BD() {
		file = new File("GravarDados.dat");
	}
	
	public void gravaDados(Sistema sistema) throws FileNotFoundException, IOException{
		@SuppressWarnings("resource")
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(sistema);
	}
	
	public void leDados(Sistema sistema) throws FileNotFoundException, IOException, ClassNotFoundException {
		@SuppressWarnings("resource")
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
		sistema = (Sistema) input.readObject();
	}
}
