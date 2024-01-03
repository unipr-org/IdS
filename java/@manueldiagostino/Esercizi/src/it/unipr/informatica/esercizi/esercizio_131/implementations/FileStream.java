package it.unipr.informatica.esercizi.esercizio_131.implementations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import it.unipr.informatica.esercizi.esercizio_131.abstractions.Stream;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class FileStream extends Stream {
	private FileOutputStream file;
	
	public FileStream(String filename) throws FileNotFoundException {
		if (filename == null)
			throw new IllegalArgumentException("filename == null");

		file = new FileOutputStream(filename);
	}
	
	public FileStream(File file) throws FileNotFoundException {
		if (file == null)
			throw new IllegalArgumentException("filename == null");

		this.file = new FileOutputStream(file);
	}
	
	@Override
	public void close() {
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void HandleBufferFull() {
		try {
			file.write(buffer);
		} catch (IOException e) {
			System.err.println("FileStream HandleBufferFull exception");
		}
	}

	public static void main(String[] args) {
		try {
			FileStream s = new FileStream("./src/it/unipr/informatica/esercizi/esercizio_131/implementations/prova1.txt");
			s.PutString("œœœœœœCiao Mondoœœœœœœ");
			
			s.HandleBufferFull();
			s.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
