package it.unipr.informatica.concurrent.pool;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface Callback<ArgType> {
	public void onSuccess(ArgType arg);
	public default void onFailure(Throwable throwable) {
		throwable.printStackTrace();
	}
}
