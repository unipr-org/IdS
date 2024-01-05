package it.unipr.informatica.patterns.prototype;

import java.util.Objects;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class MutableObject {
	public int data = 0;

	public MutableObject(int d) {
		data = d;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof MutableObject))
			return false;
		MutableObject mutObj = (MutableObject) (obj);
		return data == mutObj.data;
	}
}
