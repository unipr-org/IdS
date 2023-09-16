/*
 * RemoteResponse
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.aspects;

import java.io.Serializable;

public interface RemoteResponse extends Serializable {
	public Object getResult();

	public Throwable getException();
}
