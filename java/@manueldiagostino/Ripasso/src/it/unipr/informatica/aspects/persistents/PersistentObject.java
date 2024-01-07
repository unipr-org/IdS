package it.unipr.informatica.aspects.persistents;

import java.io.Serializable;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class PersistentObject<T> implements Serializable {
	private static final long serialVersionUID = 6786473285006345510L;
	
	private T value;

	/**
	 * @param value
	 */
	public PersistentObject(T value) {
		if (value == null)
			throw new IllegalArgumentException("value == null");
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(T value) {
		if (value == null)
			throw new IllegalArgumentException("value == null");
		this.value = value;
	}
	
	@Override
	public int hashCode() {
		return (int) (value.hashCode()+serialVersionUID);
	}
}
