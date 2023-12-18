package it.unipr.informatica.creationals.builder;

public class Main {
	private void go() {
		Student s1 = StudentBuilder.newBuilder()
				.nome("Mario")
				.cognome("Rossi")
				.comuneNascita("Roma")
				.dataDiNascita("02/04/2001")
				.facolta("Medicina")
				.universita("UniPR")
				.matricola(330183)
				.annoImmatricolazione(2022)
				.annoAttuale(3)
				.build();
		System.out.println(s1);
		
		Student s2 = StudentBuilder.newBuilder()
				.nome("Filippo")
				.cognome("Verdi")
				.comuneNascita("Napoli")
				.dataDiNascita("08/06/2003")
				.facolta("Fisica")
				.universita("UniVR")
				.matricola(530083)
				.annoImmatricolazione(2023)
				.annoAttuale(2)
				.build();
		System.out.println(s2);
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
} // ! Main
