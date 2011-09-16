package net.hisme.masaki.seiga_wallpaper;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.util.Log;

public class Downloader {
	private static final String tag = "SeigaWallpaper";

	protected int clip_id;

	public Downloader(int clip_id) {
		this.clip_id = clip_id;
	}

	public List<String> images() throws Exception {
		String url = String.format("http://afternoon-snow-8621.heroku.com/%d",
				this.clip_id);
		List<String> images = new ArrayList<String>();
		Document document = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(http_get_stream(url));
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList nodes = (NodeList) xpath.evaluate("/seiga/file", document,
				XPathConstants.NODESET);
		int len = nodes.getLength();

		for (int i = 0; i < len; i++) {
			images.add(xpath.evaluate("./text()", nodes.item(i)));
		}
		return images;
	}

	public InputStream http_get_stream(String url) throws Exception {
		return new URL(url).openConnection().getInputStream();
	}

	public String http_get(String url) throws Exception {
		Log.d(tag, "get html: " + url);
		StringBuffer buf = new StringBuffer();
		String line = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				http_get_stream(url)));
		while ((line = reader.readLine()) != null) {
			buf.append(line);
		}
		return buf.toString();
	}

	public static String download(int clip_id) throws Exception {
		try {
			Downloader downloader = new Downloader(clip_id);
			List<String> images = downloader.images();
		} catch (Exception e) {
			Log.d(tag, "Exception: " + e.getMessage());
		}
		return "";
	}
}
