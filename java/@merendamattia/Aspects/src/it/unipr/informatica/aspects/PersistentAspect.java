/*
 * PersistentAspect
 *
 * (c) 2021-2023 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.aspects;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import it.unipr.informatica.aspects.interfaces.PersistentHandler;

public class PersistentAspect {

    // Metodo per associare un oggetto serializzabile a un gestore di persistenza
    public static <T extends Serializable> PersistentHandler<T> attach(String fileName, T object) throws IOException {
        return attach(new File(fileName), object);
    }

    // Metodo per associare un oggetto serializzabile a un gestore di persistenza con un file specifico
    public static <T extends Serializable> PersistentHandler<T> attach(File file, T object) throws IOException {
        
        if (object == null)
            throw new IllegalArgumentException("object == null");

        
        if (file == null)
            throw new IllegalArgumentException("file == null");

        // Controlla se il file esiste e non è un file
        if (file.exists() && !file.isFile())
            throw new IllegalArgumentException("file.exists() && !file.isFile()");

        // Crea un gestore di persistenza interno
        InnerPersistentHandler<T> handler = new InnerPersistentHandler<T>(file);

        // Se il file esiste, carica il contenuto, altrimenti imposta l'oggetto di destinazione
        if (file.exists())
            handler.load();
        else
            handler.target = object;

        // Restituisce il gestore di persistenza
        return handler;
    }

    // Classe interna che implementa il gestore di persistenza
    private static class InnerPersistentHandler<T extends Serializable> implements PersistentHandler<T> {

        // Oggetto di destinazione
        private T target;

        // File associato al gestore di persistenza
        private File file;

        private InnerPersistentHandler(File file) {
            this.file = file;

            this.target = null;
        }

        // Metodo per ottenere l'oggetto di destinazione
        @Override
        public T get() {
            if (target == null)
                throw new IllegalStateException("target == null");
            return target;
        }

        // Metodo per ripristinare lo stato precedente dall'archivio
        @Override
        public void rollback() throws IOException {
            // Carica il contenuto del file
            load();
        }

        // Metodo per salvare l'oggetto di destinazione nello stato corrente
        @Override
        public void commit() throws IOException {
            // Salva l'oggetto di destinazione nel file
            save();
        }

        // Metodo privato per caricare il contenuto del file nell'oggetto di destinazione
        private void load() throws IOException {
        	
            try (	InputStream inputStream = new FileInputStream(file);
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)
            ) {
//        	try (InputStream inputStream = new FileInputStream(file);
//                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
//                    ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream)) {
                
            	// Legge l'oggetto serializzato dal file
                Object object = objectInputStream.readObject();

                // Esegue il cast dell'oggetto a T
                @SuppressWarnings("unchecked")
                T result = (T) object;

                // Imposta l'oggetto di destinazione con l'oggetto letto dal file
                target = result;
            
            } catch (IOException exception) {
                throw exception;
            } catch (Throwable throwable) {
                throw new IOException(throwable);
            }

            if (target == null)
                throw new IllegalStateException("target == null");
        }

        // Metodo privato per salvare l'oggetto di destinazione nel file
        private void save() throws IOException {
        	
            if (target == null)
                throw new IllegalStateException("target == null");

//            try (OutputStream outputStream = new FileOutputStream(file);
//                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
//                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream)) {
        	try (	OutputStream outputStream = new FileOutputStream(file);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)
            ) {	
                // Scrive l'oggetto di destinazione nel file
                objectOutputStream.writeObject(target);
                
            } catch (IOException exception) {
                throw exception;
            } catch (Throwable throwable) {
                throw new IOException(throwable);
            }
        }
    }
}
