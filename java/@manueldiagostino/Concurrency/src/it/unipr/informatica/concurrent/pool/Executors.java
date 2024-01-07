package it.unipr.informatica.concurrent.pool;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */


/**
 * Questa classe mette a disposizione un unico metodo static
 * per richiedere un nuovo pool di thread (numero fissato);
 * così facendo si evita che l'utente possa creare arbitrariamente
 * pool di risorse, lasciandoci la possibilità di introdurre ottimizzazioni.
 */
class Executors {
	public static ExecutorService newFixedThreadPool(int count) {
		return new SimpleThreadPoolExecutorService(count);
	}
	
	private Executors() {
		// Intentionally left blank
	}
}
