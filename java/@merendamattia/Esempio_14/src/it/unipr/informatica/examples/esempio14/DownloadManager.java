/*
 * DownloadManager
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples.esempio14;

import java.io.IOException;

public interface DownloadManager {
	public ResourceContent download(String url) throws IOException;
}
