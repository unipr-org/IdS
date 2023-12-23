package it.unipr.informatica.exercises.esercizio1_4_1;

public interface EditableFile {
	public void create(String name);
	public void delete();
	public void write(String name);
	public void read();
	public void readPartially(int begin, int end);
	public void union(EditableFile other);
	public void edit(int begin, int end);
	public void rename(String newName);
	public void save();
}
