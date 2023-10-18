/*
 * RemoteException
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.aspects;

public class RemoteException extends RuntimeException {
	private static final long serialVersionUID = -587659957946875703L;

	public RemoteException(Throwable cause) {
		super(cause);
	}
}
