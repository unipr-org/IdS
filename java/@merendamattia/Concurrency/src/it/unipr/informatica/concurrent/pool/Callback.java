package it.unipr.informatica.concurrent.pool;

public interface Callback<T> {
	public void onSuccess(T result);

	public default void onFailure(Throwable throwable) {
		throwable.printStackTrace();
	}
}
