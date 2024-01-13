package it.unipr.informatica.aspects.active.pools;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public interface Callback<ArgType> {
	public void onSuccess(ArgType arg);
	
	public default void onFailure(Throwable e) {
		e.printStackTrace();
	}
}
