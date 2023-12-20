package it.unipr.informatica.creationals.builder;

public class Student {
	private String nome;
	private String cognome;
	private String dataDiNascita;
	private String comuneNascita;
	private String universita;
	private int annoImmatricolazione;
	private String facolta;
	private int matricola;
	private int annoAttuale;
	
	public Student(String nome, String cognome, String dataDiNascita, String comuneNascita, 
			String universita, int annoImmatricolazione, String facolta, 
			int matricola, int annoAttuale) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.comuneNascita = comuneNascita;
		this.universita = universita;
		this.annoImmatricolazione = annoImmatricolazione;
		this.facolta = facolta;
		this.matricola = matricola;
		this.annoAttuale = annoAttuale;
	}
	
	@Override
	public String toString() {
		String result = "";
		
		result += cognome + " " + nome + ", nato/a a " + comuneNascita + " il " + dataDiNascita + ", ";
		result += "iscritto presso " + universita + ", facolta' di " + facolta + " (aa " + annoImmatricolazione + "), ";
		result += "attualmente e' al " + annoAttuale + " anno di studi con la matricola " + matricola;
		
		return result;
	}
	
} // ! Student
