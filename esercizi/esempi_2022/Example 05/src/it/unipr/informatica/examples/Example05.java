/*
 * Example05
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

public class Example05 {
	private void go() {
		DownloadManager downloadManager = new DownloadManager(4);

		downloadManager.download("https://www.google.it");
		downloadManager.download("https://www.youtube.it");
		downloadManager.download("https://www.amazon.it");
		downloadManager.download("https://www.missingwebsite.com");
		downloadManager.download("https://www.ebay.it");
		downloadManager.download("https://www.unipr.it");

		try {
			Thread.sleep(10000);

			downloadManager.shutdown();
		} catch (InterruptedException interruptedException) {
			return;
		}
	}

	public static void main(String[] args) {
		new Example05().go();
	}
}
