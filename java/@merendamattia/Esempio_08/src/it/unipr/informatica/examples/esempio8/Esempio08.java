package it.unipr.informatica.examples.esempio8;
/*
 * Questo programma utilizza le API di riflessione di Java per ottenere 
 * e visualizzare le informazioni sulla classe fornita dall'utente.
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Locale;
import java.util.Scanner;

public class Esempio08 {
	/*
	 * Mostra il nome della classe, la classe base (se presente), 
	 * le interfacce implementate, i campi, i costruttori e i metodi.
	 */
	private void dump(Class<?> clazz) {
		System.out.println("Class: " + clazz.getName());

		// ------------------------------------------ Classe base
		Class<?> baseClass = clazz.getSuperclass();
		if (baseClass != null)
			System.out.println("Base class: " + baseClass.getName());

		// ------------------------------------------ Interfacce
		Class<?>[] interfaces = clazz.getInterfaces();
		for (int i = 0; i < interfaces.length; ++i)
			System.out.println("Implemented interface: " + interfaces[i].getName());

		// ------------------------------------------ Campi
		Field[] fields = clazz.getFields();
		for (int i = 0; i < fields.length; ++i) {
			Field field = fields[i];
			Class<?> fieldClass = field.getType();
			System.out.println("Field: " + fieldClass.getName() + " " + field.getName());
		}

		// ------------------------------------------ Costruttori
		Constructor<?>[] constructors = clazz.getConstructors();
		for (int i = 0; i < constructors.length; ++i) {
			Constructor<?> constructor = constructors[i];
			System.out.print("Constructor: " + constructor.getName() + "(");
			Parameter[] parameters = constructor.getParameters();

			for (int j = 0; j < parameters.length; ++j) {
				Parameter parameter = parameters[j];
				Class<?> parameterClass = parameter.getType();
				System.out.print(parameterClass.getName() + " " + parameter.getName());

				if (j != parameters.length - 1)
					System.out.print(", ");
			}
			System.out.println(")");
		}

		// ------------------------------------------ Metodi
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; ++i) {
			Method method = methods[i];
			Class<?> resultType = method.getReturnType();
			System.out.print("Method: " + resultType.getName() + " " + method.getName() + "(");
			Parameter[] parameters = method.getParameters();
			
			for (int j = 0; j < parameters.length; ++j) {
				Parameter parameter = parameters[j];
				Class<?> parameterClass = parameter.getType();
				System.out.print(parameterClass.getName() + " " + parameter.getName());

				if (j != parameters.length - 1)
					System.out.print(", ");
			}
			System.out.println(")");
		}
	}

	/*
	 * Questo metodo richiama il metodo `dump()` dopo aver caricato la classe basata sul nome 
	 * completamente qualificato fornito come argomento.
	 * Gestisce l'eccezione ClassNotFoundException se non riesce a caricare la classe.
	 */
	private void show(String className) {
		try {
			Class<?> clazz = Class.forName(className);

			dump(clazz);
		} catch (ClassNotFoundException classNotFoundException) {
			System.err.println("Cannot load " + className);
		}
	}

	private void go() {
		// try-catch con risorse -> le rilascio quando esco dal blocco
		try (
				Scanner scanner = new Scanner(System.in)
				) {
			
			scanner.useLocale(Locale.US); 
			/*
			 * Questo è utile quando si leggono input numerici dalla tastiera e ci si aspetta 
			 * che i numeri abbiano il formato della locale statunitense, che utilizza 
			 * il punto come separatore decimale e la virgola come separatore delle migliaia.
			 */
			
			System.out.print("Please, enter the fully qualified name of a class: ");
			String className = scanner.nextLine();

			show(className);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	/*
	 * La sintassi Class<?> in Java fa parte del sistema di riflessione ed è utilizzata 
	 * per rappresentare l'oggetto Class di una classe sconosciuta o non specificata.
	 * 
	 * Class: Class è una classe in Java che rappresenta metadati sulla classe di un oggetto, 
	 * inclusi i dettagli sulla sua struttura, campi, metodi e altro ancora. È parte del sistema di riflessione di Java.
	 * 
	 * <T>: È un parametro generico che indica un tipo. Quando si utilizza <T> dopo Class, 
	 * si sta dicendo a Java di lavorare con un oggetto Class che rappresenta il tipo specifico di classe. 
	 * Ad esempio, Class<String> rappresenta l'oggetto Class di una classe di tipo String.
	 * 
	 * <?>: Questa è una wildcard (?) che indica "qualsiasi tipo". Quando combinata con Class, 
	 * Class<?> significa un oggetto Class di una classe di tipo sconosciuto. Può essere qualsiasi classe.
	 */
	public static void main(String[] args) {
		new Esempio08().go();
	}
}
