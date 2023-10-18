/*
 * Example15Client
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

import it.unipr.informatica.aspects.RemoteAspect;

public class Example15Client {
	private void go() {
		try {
			FileManager fileManager = RemoteAspect.connect(FileManager.class, "127.0.0.1", 1704);

			String[] fileNames = fileManager.listFileNames(".");

			for (String fileName : fileNames) {
				byte[] file = fileManager.getFile(fileName);

				System.out.println("Received " + file.length + " bytes for file " + fileName);
			}

			fileManager.getFile("missingFile");
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Example15Client().go();
	}
}
