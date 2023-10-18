/*
 * DownloadManager
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import it.unipr.informatica.concurrent.ExecutorService;
import it.unipr.informatica.concurrent.Executors;
import it.unipr.informatica.concurrent.Future;

public final class DownloadManager {
	private static final int BUFFER_SIZE = 1024;

	private ExecutorService executorService;

	public DownloadManager(int connections) {
		if (connections < 1)
			throw new IllegalArgumentException("connections < 1");

		this.executorService = Executors.newFixedThreadPool(connections);
	}

	public void shutdown() {
		executorService.shutdown();
	}

	public Future<ResourceContent> download(String url) {
		if (url == null)
			throw new IllegalArgumentException("url == null");

		return executorService.submit(() -> downloadResourceContent(url));
	}

	private ResourceContent downloadResourceContent(String url) throws IOException {
		try (InputStream inputStream = new URL(url).openStream();
				BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			byte[] buffer = new byte[BUFFER_SIZE];

			int read = bufferedInputStream.read(buffer);

			while (read >= 0) {
				outputStream.write(buffer, 0, read);

				read = bufferedInputStream.read(buffer);
			}

			byte[] data = outputStream.toByteArray();

			return new ResourceContent(url, data);
		}
	}
}
