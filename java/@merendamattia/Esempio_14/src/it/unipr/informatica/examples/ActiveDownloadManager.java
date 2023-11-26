/*
 * ActiveDownloadManager
 *
 * (c) 2021-2022 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

import it.unipr.informatica.aspects.interfaces.Active;
import it.unipr.informatica.concurrent.pool.Callback;
import it.unipr.informatica.concurrent.pool.Future;

public interface ActiveDownloadManager extends Active<DownloadManager> {
	public Future<ResourceContent> download(String url);

	public void download(String url, Callback<ResourceContent> callback);
}
