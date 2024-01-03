package it.unipr.informatica.esercizi.esercizio_131.implementations;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

import it.unipr.informatica.esercizi.esercizio_131.abstractions.Decorator;
import it.unipr.informatica.esercizi.esercizio_131.abstractions.Stream;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class AsciiStream extends Decorator {
	private Stream target;
	
	public AsciiStream(Stream stream) {
		this.target = stream;
	}
	
	@Override
	public void PutInt(int integer) {
		target.PutString(Integer.toString(integer));
	}
	
	@Override
	public void PutString(String s) {
		byte[] bytes = s.getBytes();
		target.PutString(new String(bytes, StandardCharsets.US_ASCII));
	}
	
	@Override
	public void HandleBufferFull() {
		
		target.HandleBufferFull();
	}

	@Override
	public void close() {
		target.close();
	}
	
	public static void main(String[] args) {
		try {
			String path = "./src/it/unipr/informatica/esercizi/esercizio_131/implementations/prova1_ascii.txt";
			Stream s = new AsciiStream(new FileStream(path));
			
			s.PutString("œœœœœœCiao Mondoœœœœœœ");
			s.HandleBufferFull();
			
			s.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
