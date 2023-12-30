package it.unipr.informatica.examples.esempio5;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

import it.unipr.informatica.concurrent.pool.ExecutorService;
import it.unipr.informatica.concurrent.pool.Executors;

public final class DownloadManager {
	private static final int BUFFER_SIZE = 1024;

	private ExecutorService executorService;

	public DownloadManager(int connections) {
		if (connections < 1)
			throw new IllegalArgumentException("connections < 1");

		this.executorService = Executors.newFixedThreadPool(connections); // connections = numero thread
	}

	public void shutdown() {
		executorService.shutdown();
	}

	public void download(String url) {
		if (url == null)
			throw new IllegalArgumentException("url == null");

		executorService.execute(() -> downloadAndPrint(url));
	}

	private void downloadAndPrint(String url) {
		/*
			Il costrutto `try-with-resources` in Java è una forma di try statement che dichiara uno 
			o più oggetti che devono essere chiusi alla fine del blocco try. 
		 */
		try (
				InputStream inputStream = new URL(url).openStream();
				BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
						) {
			byte[] buffer = new byte[BUFFER_SIZE];

			int read = bufferedInputStream.read(buffer);

			while (read >= 0) {
				outputStream.write(buffer, 0, read);

				read = bufferedInputStream.read(buffer);
			}

			byte[] data = outputStream.toByteArray();

			System.out.println("Downloaded " + data.length + " bytes from " + url);
		} catch (Throwable throwable) {
			System.err.println("Cannot download with error: " + throwable.getMessage());
		}
	}
}