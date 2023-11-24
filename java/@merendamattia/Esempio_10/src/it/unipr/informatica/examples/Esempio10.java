/*
 * Esempio10
 *
 * (c) 2021-2023 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

import java.util.List;

import it.unipr.informatica.beans.Bean;
import it.unipr.informatica.beans.BeanLoader;
import it.unipr.informatica.examples.model.Book;
import it.unipr.informatica.examples.model.Student;

public class Esempio10 {
    
    private void go() {
        try {
            // Creazione di un oggetto BeanLoader
            BeanLoader loader = new BeanLoader();

            // Caricamento degli oggetti Student da un file CSV
            List<Student> studentBeans = loader.load(Student.class, "Students.csv");

            // Stampa dei cognomi degli studenti
            for (Student student : studentBeans)
                System.out.println(student.getSurname());

            System.out.println();

            // Caricamento degli oggetti Book da un file CSV
            List<Book> bookBeans = loader.load(Book.class, "Books.csv");

            // Stampa degli oggetti Book (il metodo toString è nascosto)
            for (Bean bean : bookBeans)
                System.out.println(bean);
            
        } catch (Throwable throwable) {
            // Gestione delle eccezioni durante il caricamento degli oggetti
            System.err.println("Cannot load beans with message " + throwable.getMessage());
        }
    }

    /*
     * L'obiettivo è utilizzare la riflessione per creare istanze di oggetti Bean basate su un file di input specifico.
     */
    public static void main(String[] args) {
        new Esempio10().go();
    }
}
