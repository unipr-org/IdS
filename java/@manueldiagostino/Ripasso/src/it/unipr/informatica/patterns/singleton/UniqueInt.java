package it.unipr.informatica.patterns.singleton;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class UniqueInt {
	private static volatile UniqueInt instance;
	private static Integer value;

	public static UniqueInt getInstance() {
		if (instance == null)
			createInstance();

		return instance;
	}

	public static Integer getValue() {
		if (instance == null)
			createInstance();

		return value;
	}

	public static void setValue(Integer value) {
		if (instance == null)
			createInstance();

		synchronized (UniqueInt.class) {
			UniqueInt.value = value;
		}
	}

	private static void createInstance() {
		synchronized (UniqueInt.class) {
			if (instance == null) {
				instance = new UniqueInt();
				value = Integer.MAX_VALUE;
			}
		}
	}

	private UniqueInt() {

	}
}
