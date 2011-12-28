package net.hisme.masaki.seiga_wallpaper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPClient {

	/**
	 * GET url and return response as String
	 * 
	 * @param url
	 * @return response as String
	 * @throws ConnectionError
	 */
	public static String get(String url) throws MalformedURLException {
		StringBuffer buf = new StringBuffer();
		String line = null;

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					getStream(url)));
			while ((line = reader.readLine()) != null) {
				buf.append(line);
			}
			return buf.toString();
		} catch (MalformedURLException e) {
			throw e;
		} catch (IOException e) {
			App.Log.d("Read Error:" + url);
			throw new ConnectionError();
		}
	}

	/**
	 * GET url and return response as InputStrem
	 * 
	 * @param url
	 * @return response as InputStream
	 * @throws ConnectionError
	 * @throws MalformedURLException
	 */
	public static InputStream getStream(String url)
			throws MalformedURLException {
		try {
			return new URL(url).openConnection().getInputStream();
		} catch (MalformedURLException e) {
			App.Log.e("Malformed Url: " + url);
			throw e;
		} catch (IOException e) {
			App.Log.d("Connection Error: " + url);
			throw new ConnectionError();
		}

	}

	public static class ConnectionError extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}
}
