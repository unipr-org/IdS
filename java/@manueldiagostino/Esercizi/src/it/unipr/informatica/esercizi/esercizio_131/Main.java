package it.unipr.informatica.esercizi.esercizio_131;

import it.unipr.informatica.esercizi.esercizio_131.abstractions.Stream;
import it.unipr.informatica.esercizi.esercizio_131.implementations.AsciiStream;
import it.unipr.informatica.esercizi.esercizio_131.implementations.FileStream;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Main {
	public static void main(String[] args) {
		try {
			
			// Senza decorator
			String path1 = "./src/it/unipr/informatica/esercizi/esercizio_131/implementations/prova.txt";
			Stream fileStream = new FileStream(path1);
			fileStream.PutString("œœœœœœCiao Mondoœœœœœœ");
			
			fileStream.HandleBufferFull();
			fileStream.close();
			
			
			// Con decorator
			String path2 = "./src/it/unipr/informatica/esercizi/esercizio_131/implementations/prova_ascii.txt";
			Stream decoratedStream = new AsciiStream(new FileStream(path2));
			
			decoratedStream.PutString("œœœœœœCiao Mondoœœœœœœ");
			decoratedStream.HandleBufferFull();
			
			decoratedStream.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
