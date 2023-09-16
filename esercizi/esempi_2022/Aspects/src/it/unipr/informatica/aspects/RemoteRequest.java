/*
 * RemoteRequest
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.aspects;

import java.io.Serializable;

public interface RemoteRequest extends Serializable {
	public String getMethodName();

	public String[] getParameterTypeNames();

	public Object[] getArguments();
}
