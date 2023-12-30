/*
 * FileManager
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples.esempio15;

import java.io.IOException;

public interface FileManager {
	public String[] listFileNames(String folderName) throws IOException;

	public byte[] getFile(String path) throws IOException;
}
