package net.hisme.masaki.seiga_wallpaper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class HTTP_Client {

	public static String get(String url) throws Exception {
		StringBuffer buf = new StringBuffer();
		String line = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				get_stream(url)));
		while ((line = reader.readLine()) != null) {
			buf.append(line);
		}
		return buf.toString();
	}

	public static InputStream get_stream(String url) throws Exception {
		return new URL(url).openConnection().getInputStream();
	}

}
