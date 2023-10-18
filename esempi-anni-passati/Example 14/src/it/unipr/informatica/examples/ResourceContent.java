/*
 * ResourceContent
 *
 * (c) 2021 Federico Bergenti. All Rights Reserved.
 */
package it.unipr.informatica.examples;

public final class ResourceContent {
	private String url;
	
	private byte[] data;

	public ResourceContent(String url, byte[] data) {
		if (url == null || url.length() == 0)
			throw new IllegalArgumentException("url == null || url.length() == 0");

		if (data == null)
			throw new IllegalArgumentException("data == null");

		this.url = url;

		this.data = data;
	}

	public String getURL() {
		return url;
	}

	public byte[] getData() {
		return data;
	}
}
