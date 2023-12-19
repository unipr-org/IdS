package it.unipr.informatica.aspects;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.Serializable;

public class PersistenAspect <T extends Serializable> {
	
	public static <T extends Serializable> PersistentHandler<T> attach(String fileName, T target) throws Exception{
		return attach(new File(fileName), target);
	}
	
	public static <T extends Serializable> PersistentHandler<T> attach(File file, T target) throws Exception{
		if(file == null)
			throw new IllegalArgumentException("file == null");
		if(target == null)
			throw new IllegalArgumentException("target == null");
		
		if(file.exists() && !file.isFile())
			throw new IllegalArgumentException("file.exists() && !file.isFile()");
		
		InnerPersistentHandler<T> handler = new InnerPersistentHandler<T>(file, target);
		
		if(file.exists())
			handler.load();
		else
			handler.target_ = target;
		
		return handler;
		
	}
	
	private static class InnerPersistentHandler<T extends Serializable> implements PersistentHandler<T> {
		
		private T target_;
		private File file_;
		
		public InnerPersistentHandler(File file, T target) {
			
			this.file_ = file;
			
			this.target_ = target;
		}
		
		@Override
		public T get() {
			return target_;
		}
		@Override
		public void commit() throws IOException {
			save();
		}

		@Override
		public void rollback() throws IOException {
			load();
		}

		@SuppressWarnings("unchecked")
		public void load() throws IOException {
			try(
					InputStream input = new FileInputStream(file_);
					BufferedInputStream buffer = new BufferedInputStream(input);
					ObjectInputStream inpuObject = new ObjectInputStream(buffer)
				){
				target_ = (T) inpuObject.readObject();
				
				if(target_ == null)
					throw new IOException("target_ == null");
				
			} catch (IOException e) {
				throw e;
			} catch (Exception e) {
				
			}
		}

		public void save() throws IOException {
			try(
					OutputStream out = new FileOutputStream(file_);
					BufferedOutputStream buffer = new BufferedOutputStream(out);
					ObjectOutputStream outObject = new ObjectOutputStream(buffer)
				){
				outObject.writeObject(target_);
				
				if(target_ == null)
					throw new IOException("target_ == null");
				
			} catch (IOException e) {
				throw e;
			}
		}
		
	}
}
