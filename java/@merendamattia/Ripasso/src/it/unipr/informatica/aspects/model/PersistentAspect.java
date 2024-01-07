package it.unipr.informatica.aspects.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PersistentAspect {
	
	public static <T extends Serializable> PersistentHandler<T> attach(String fileName, T target) throws IOException {
		return attach(new File(fileName), target);
	}
	
	public static <T extends Serializable> PersistentHandler<T> attach(File file, T target) throws IOException {		
		return new InnerPersistentHandler<T>(file, target);
	}
	
	private static class InnerPersistentHandler<T extends Serializable> implements PersistentHandler<T> {
		private File file;
		private T target;
		
		public InnerPersistentHandler(File file, T target) throws IOException {
			if(file == null)
				throw new IllegalArgumentException("file == null");
			
			this.file = file;
			
			if(target == null)
				throw new IllegalArgumentException("target == null");
			
			if(file.exists() && !file.isFile())
				throw new IllegalArgumentException("file.exists() && !file.isFile()");
			
			if(file.exists())
				load();
			else
				this.target = target;
		}

		@Override
		public T get() {
			return target;
		}

		@Override
		public void commit() throws IOException {
			save();
		}

		@Override
		public void rollback() throws IOException {
			load();
		}
		
		// Carica le informazioni dal file deserializzando l'oggetto
		@SuppressWarnings("unchecked")
		private void load() throws IOException {
			
			try (	InputStream inputStream = new FileInputStream(file);
					ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)
			){
				
				
				target = (T) objectInputStream.readObject();
				
				
				if(target == null)
					throw new IOException("target == null");
					
			} catch (IOException e) {
				throw e;
			} catch (Throwable e) {
				throw new IOException(e);
			}
			
		}
		
		// Scrive le informazioni sul file serializzando l'oggetto
		private void save() throws IOException {
			try (	OutputStream outputStream = new FileOutputStream(file);
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)
			){
				
				
				objectOutputStream.writeObject(target);

					
			} catch (IOException e) {
				throw e;
			} catch (Throwable e) {
				throw new IOException(e);
			}
		}
		
	} // ! InnerPersistentHandler
} // ! PersistentAspect
