package it.unipr.informatica.exercises.esercizio1_4_1;

public class Main {
	private void go() {
		EditableFile file = new EditableFileImpl("src/it/unipr/informatica/exercises/esercizio1_4_1/files/");
		file.create("myTestFile.txt");
		// file.delete();
		file.write("Questo e' il mio file di testo di prova\n Riga2\n Riga3\n Riga4\n Riga5");
		// file.read();
		// file.readPartially(1, 4);
		file.rename("pippo.txt");
	}
	
	public static void main(String[] args) {
		new Main().go();
	}
} // ! Main

// TODO da sistemare readPartially() e rename