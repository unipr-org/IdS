package it.unipr.informatica.executors.model;

public interface Callback<T> {
	public void onSuccess(T value);
	public default void onFailure(Throwable t) {
		t.printStackTrace();
	}
}
