package it.unipr.informatica.aspects.persistents;

import java.awt.image.RescaleOp;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 * 
 * Obiettivo: fornire un aspetto che permetta la persistenza degli 
 * oggetti della programmazione OO. Sfruttiamo i `serializable` di Java.
 */
public class PersistentAspect {
	public static <T extends Serializable > PersistentHandler<T> attach(T object, File file) {
		if (object == null)
			throw new IllegalArgumentException("object == null");
		if (file == null)
			throw new IllegalArgumentException("file == null");
		
		return new InnerPersistentHandler<T>(object, file);
	}
	
	private static class InnerPersistentHandler<T extends Serializable> implements PersistentHandler<T> {
		private T target;
		private File file;
		
		public InnerPersistentHandler(T target, File file) {
			this.target = target;
			
			if (file.exists() && !file.isFile())
				throw new IllegalArgumentException("file.exists() && !file.isFile()");
			
			this.file = file;
		}
		
		@Override
		public T get() {
			if (target == null)
				throw new IllegalArgumentException("target == null");
			return target;
		}

		@Override
		public void commit() throws IOException {
			save();
		}
		
		private void save() throws IOException {
			try (	OutputStream outputStream = new FileOutputStream(file);
					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
				) {
						
				objectOutputStream.writeObject(target);
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public void rollback() throws IOException {
			try (	InputStream inputStream = new FileInputStream(file);
					BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
					ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
				) {
						
				try {
					Object result = objectInputStream.readObject();
					target = (T) result;
					
				} catch (ClassNotFoundException e) {
					throw new IOException(e.getMessage());
				} catch (IOException e) {
					throw e;
				}
				
				if (target == null)
					throw new IllegalStateException("target == null after readObject()");
			}
		}
	}
	
	private static void check(PersistentHandler<LinkedList<PersistentObject<String>>> handler) throws IOException {
		handler.rollback();
		Deque<PersistentObject<String>> deque = handler.get();
		
		for (PersistentObject<String> o : deque)
			System.out.println(o.getValue());
	}
	
	public static void main(String[] args) throws IOException {
		String path = "./src/it/unipr/informatica/aspects/persistents/persistent.dat";
		
		PersistentHandler<LinkedList<PersistentObject<String>>> handler = PersistentAspect.attach(new LinkedList<PersistentObject<String>>(), new File(path));
		
		// modifiche all'oggetto
		Deque<PersistentObject<String>> deque = handler.get();
		deque.add(new PersistentObject<String>("Alessia"));
		deque.add(new PersistentObject<String>("Alex"));
		deque.add(new PersistentObject<String>("Manuel"));
		deque.add(new PersistentObject<String>("Simona"));
		
		handler.commit();
		deque.add(new PersistentObject<String>("Zorro")); // non viene salvato
		
		check(handler);
	}
}
