package it.unipr.informatica.aspects;

import java.io.IOException;
import java.io.Serializable;

public interface PersistentHandler <T extends Serializable>{
	public T get();
	public void commit() throws IOException;
	public void rollback() throws IOException;
}
