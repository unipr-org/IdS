/*
 * SimpleFileManager
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class SimpleFileManager implements FileManager {
	private static final int BUFFER_SIZE = 1024;

	private String basePath;

	public SimpleFileManager(String basePath) {
		if (basePath == null || basePath.length() == 0)
			throw new IllegalArgumentException("basePath == null || basePath.length() == 0");

		this.basePath = basePath;
	}

	@Override
	public String[] listFileNames(String folderName) throws IOException {
		String fullName = basePath + File.separator + folderName;

		File folder = new File(fullName);

		if (!folder.isDirectory())
			throw new IOException("!folder.isDirectory()");

		List<String> list = new LinkedList<>();

		for (File file : folder.listFiles()) {
			if (file.isFile())
				list.add(file.getName());
		}

		return list.toArray(new String[list.size()]);
	}

	@Override
	public byte[] getFile(String path) throws IOException {
		String fullPath = basePath + File.separator + path;

		File file = new File(fullPath);

		if (!file.isFile())
			throw new IOException("!file.isFile()");

		try (InputStream inputStream = new FileInputStream(file);
				BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			byte[] buffer = new byte[BUFFER_SIZE];

			int read = bufferedInputStream.read(buffer);

			while (read >= 0) {
				outputStream.write(buffer, 0, read);

				read = bufferedInputStream.read(buffer);
			}

			return outputStream.toByteArray();
		} catch (IOException exception) {
			throw exception;
		}
	}
}
