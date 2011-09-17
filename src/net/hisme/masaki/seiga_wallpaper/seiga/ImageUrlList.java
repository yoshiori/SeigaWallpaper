package net.hisme.masaki.seiga_wallpaper.seiga;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import net.hisme.masaki.seiga_wallpaper.SeigaWallpaper;

import android.content.Context;

public class ImageUrlList {
	private static final String url_file = "image_urls.txt";
	private List<String> images;

	public ImageUrlList() throws Exception {
		this.images = new ArrayList<String>();
		load();
	}

	public String random() {
		return images.get((int) Math.ceil(Math.random() * images.size()));
	}

	private void load() throws Exception {
		images = new ArrayList<String>();

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				SeigaWallpaper.instance().openFileInput(ImageUrlList.url_file)));
		String line = null;
		while ((line = reader.readLine()) != null) {
			images.add(line);
		}
	}

	public void save(List<String> images) throws Exception {
		this.images = images;
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				SeigaWallpaper.instance().openFileOutput(ImageUrlList.url_file,
						Context.MODE_PRIVATE)));
		for (String url : images) {
			writer.write(url);
			writer.newLine();
		}
		writer.close();
	}
}
