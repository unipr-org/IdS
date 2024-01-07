/*
 * Example15Client
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples.esempio15;

import it.unipr.informatica.aspects.ActiveAspect;
import it.unipr.informatica.aspects.RemoteAspect;
import it.unipr.informatica.aspects.model.Active;
import it.unipr.informatica.aspects.model.ActiveHandler;
import it.unipr.informatica.concurrent.pool.Callback;
import it.unipr.informatica.concurrent.pool.Future;

public class Esempio15Client {
	private void go() {
		try {
			FileManager fileManager = RemoteAspect.connect(FileManager.class, "127.0.0.1", 1704);

			ActiveHandler<ActiveFileManager> fileManagerHandler = ActiveAspect.attach(ActiveFileManager.class, fileManager);
			
			ActiveFileManager activeFileManager = fileManagerHandler.get();
			
			Future<String[]> fileNamesFuture = activeFileManager.listFileNames(".");

			String[] fileNames = fileNamesFuture.get();
			
			for (String fileName : fileNames) {
				activeFileManager.getFile(fileName, (file) -> {
					System.out.println("Received " + file.length + " bytes for file " + fileName);					
				});
			}

			fileManager.getFile("missingFile");
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Esempio15Client().go();
	}
	
	private static interface ActiveFileManager extends Active<FileManager> {
		public Future<String[]> listFileNames(String path);
		
		public void getFile(String path, Callback<byte[]> callback);
	}
}
