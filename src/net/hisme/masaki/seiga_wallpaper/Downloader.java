package net.hisme.masaki.seiga_wallpaper;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import android.content.Context;
import android.util.Log;

public class Downloader {
	private static final String tag = "SeigaWallpaper";
	private static final String urls_file = "image_urls.txt";

	protected int clip_id;

	public Downloader(int clip_id) {
		this.clip_id = clip_id;
	}

	public void save_image_urls(List<String> image_urls) throws Exception {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				SeigaWallpaper.context().openFileOutput(urls_file,
						Context.MODE_PRIVATE)));
		for (String url : image_urls) {
			writer.write(url);
			writer.newLine();
		}
		writer.close();
	}

	public ArrayList<String> load_image_urls() throws Exception {
		ArrayList<String> images = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				SeigaWallpaper.context().openFileInput(urls_file)));
		String line = null;
		while ((line = reader.readLine()) != null) {
			images.add(line);
		}
		return images;
	}

	public void download_image(String url) {

	}

	public static String download(int clip_id) throws Exception {
		try {
			Downloader downloader = new Downloader(clip_id);
			List<String> images = new Clip(clip_id).image_urls();
			downloader.save_image_urls(images);
		} catch (Exception e) {
			Log.d(tag, String.format("Exception: %s", e.getMessage()));
		}
		return "";
	}
}
