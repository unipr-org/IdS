package it.unipr.informatica.executors;

public interface Callback<ArgType> {
	public void onSuccess(ArgType arg);

	public default void onFailure(Throwable throwable) {
		throwable.printStackTrace();
	}
}
