package it.unipr.informatica.examples;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Locale;
import java.util.Scanner;

public class Example08 {
	private void dump(Class<?> clazz) {
		System.out.println("Class: " + clazz.getName());

		Class<?> baseClass = clazz.getSuperclass();

		if (baseClass != null)
			System.out.println("Base class: " + baseClass.getName());

		// restutuiscimi la lista delle interfacce
		Class<?>[] interfaces = clazz.getInterfaces();

		for (int i = 0; i < interfaces.length; ++i)
			System.out.println("Implemented interface: " + interfaces[i].getName());

		// ritorna i descrittori dei campi
		Field[] fields = clazz.getFields();

		for (int i = 0; i < fields.length; ++i) {
			Field field = fields[i];

			Class<?> fieldClass = field.getType();

			System.out.println("Field: " + fieldClass.getName() + " " + field.getName());
		}

		// qui si ottiene la lista di costruttori
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

		// qui si ottiene la lista dei costruttori
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

	private void show(String className) {
		// perchè for name può lanciare class not fount
		try {
			// scrivere solo Class senza <?> non è errore perchè a tempo di esecuzione mi aspetto che non ci sarà a tempo di esecuzione
			// ? indica un parametro generico che non ci sarà a tempo di esecuzione, nota il compilatore darà solo una warning
			Class<?> clazz = Class.forName(className);

			dump(clazz);
		} catch (ClassNotFoundException classNotFoundException) {
			System.err.println("Cannot load " + className);
		}
	}

	private void go() {

		// se si mettono degli spazi vengono presi all'interno del nome
		try (Scanner scanner = new Scanner(System.in)) {

			// Lo scanner è localizzato e prende la lingua del computer,
			// quindi si può impostare i caratteri ammessi come segue
			scanner.useLocale(Locale.US);
			
			System.out.print("Please, enter the fully qualified name of a class: ");

			String className = scanner.nextLine();

			show(className);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Example08().go();

		// note
		// si possono vedere solo i metodi non privati perché il compilatore può metterli inline
		// i nomi degli argomenti dei metodi "sono casuali" perché vengono scelti dal compilatore
		// sono presenti anche i metori ereditati dalle superclassi
		// si può accedere alle innerclass se sono pubbliche
		// non si vanno a violare le regole di sicurezza (visibilità privata e di package)

		// in questo modo si può riscrivere le informazioni per descrivere le interfacce

	}
}
