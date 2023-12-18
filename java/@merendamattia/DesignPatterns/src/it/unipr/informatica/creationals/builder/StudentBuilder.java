package it.unipr.informatica.creationals.builder;

public final class StudentBuilder {
	private String nome;
	private String cognome;
	private String dataDiNascita;
	private String comuneNascita;
	private String universita;
	private int annoImmatricolazione;
	private String facolta;
	private int matricola;
	private int annoAttuale;
	
	public static StudentBuilder newBuilder() {
		return new StudentBuilder();
	}
	
	public StudentBuilder nome(String nome) {
		this.nome = nome;
		return this;
	}
	public StudentBuilder cognome(String cognome) {
		this.cognome = cognome;
		return this;
	}
	public StudentBuilder dataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
		return this;
	}
	public StudentBuilder comuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
		return this;
	}
	public StudentBuilder universita(String universita) {
		this.universita = universita;
		return this;
	}
	public StudentBuilder annoImmatricolazione(int annoImmatricolazione) {
		this.annoImmatricolazione = annoImmatricolazione;
		return this;
	}
	public StudentBuilder facolta(String facolta) {
		this.facolta = facolta;
		return this;
	}
	public StudentBuilder matricola(int matricola) {
		this.matricola = matricola;
		return this;
	}
	public StudentBuilder annoAttuale(int annoAttuale) {
		this.annoAttuale = annoAttuale;
		return this;
	}
	
	public Student build() {
		return new Student(nome, cognome, dataDiNascita, comuneNascita, universita, annoImmatricolazione, facolta, matricola, annoAttuale);
	}
} // ! StudentBuilder
