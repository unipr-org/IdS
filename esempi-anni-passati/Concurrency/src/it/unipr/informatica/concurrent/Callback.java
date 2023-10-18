/*
 * Callback<T>
 * 
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.concurrent;

public interface Callback<T> {
	public void onSuccess(T result);
	
	public default void onFailure(Throwable throwable) {
		throwable.printStackTrace();
	}
}
