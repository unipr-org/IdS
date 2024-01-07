/*
 * Example14
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples.esempio14;

import it.unipr.informatica.aspects.ActiveAspect;
import it.unipr.informatica.aspects.model.ActiveHandler;
import it.unipr.informatica.concurrent.pool.Future;

public class Esempio14 {
	private void process(ResourceContent content) {
		System.out.println("Downloaded " + content.getData().length + " bytes from " + content.getURL());
	}

	private void go() {
	    // Creazione di un DownloadManager con implementazione SimpleDownloadManager
	    DownloadManager downloadManager = new SimpleDownloadManager();

	    // Collegamento di un'istanza di ActiveDownloadManager al DownloadManager utilizzando ActiveAspect
	    ActiveHandler<ActiveDownloadManager> downloadManagerHandler = ActiveAspect.attach(
																	    		ActiveDownloadManager.class, 
																	    		downloadManager);

	    // Ottenimento dell'istanza di ActiveDownloadManager dal suo handler
	    ActiveDownloadManager activeDownloadManager = downloadManagerHandler.get();

	    // Esecuzione di un download asincrono con un callback per gestire il contenuto
	    activeDownloadManager.download("https://www.unipr.it", (ResourceContent content) -> process(content));

	    // Esecuzione di un download asincrono e ottenimento di un Future per il suo risultato
	    Future<ResourceContent> future = activeDownloadManager.download("https://www.google.it");

	    try {
	        // Attendi il completamento del download e ottieni il risultato
	        ResourceContent content = future.get();

	        // Processa il contenuto ottenuto
	        process(content);
	        
	    } catch (Throwable throwable) {
	        throwable.printStackTrace();
	    }

	    // Arresto del pool di thread associato all'ActiveDownloadManager
	    downloadManagerHandler.shutdown();
	}


	public static void main(String[] args) {
		new Esempio14().go();
	}
}
