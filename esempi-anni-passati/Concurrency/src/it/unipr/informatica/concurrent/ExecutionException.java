/*
 * ExecutionException
 * 
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.concurrent;

public class ExecutionException extends Exception {
	private static final long serialVersionUID = -1644619538323674773L;

	public ExecutionException(Throwable cause) {
		super(cause);
	}
}
