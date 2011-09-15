package net.hisme.masaki.seiga_wallpaper;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import android.util.Log;

public class Downloader {
	private static final String tag = "SeigaWallpaper";

	protected String seiga_url;

	public Downloader(String url) {
		this.seiga_url = url;
	}

	public List<String> images() throws Exception {
		return images(this.seiga_url, "");
	}

	public List<String> images(String url, String params) throws Exception {
		List<String> images = new ArrayList<String>();
		String html = http_get(this.seiga_url + params);
		return images;
	}

	public String http_get(String url) throws Exception {
		Log.d(tag, "get html: " + url);
		StringBuffer buf = new StringBuffer();
		String line = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new URL(url).openConnection().getInputStream()));
		while ((line = reader.readLine()) != null) {
			Log.d(tag, line);
			buf.append(line);
		}
		return buf.toString();
	}

	public static String download(String url) throws Exception {
		try {
			Downloader downloader = new Downloader(url);
			downloader.images();
		} catch (Exception e) {
			Log.d(tag, "Exception: " + e.getMessage());
		}
		return "";
	}
}
