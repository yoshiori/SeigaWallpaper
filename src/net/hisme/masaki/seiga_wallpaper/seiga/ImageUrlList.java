package net.hisme.masaki.seiga_wallpaper.seiga;

import static net.hisme.masaki.seiga_wallpaper.App.APP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import net.hisme.masaki.seiga_wallpaper.App;

import android.content.Context;

public class ImageUrlList {
	private static final String url_file = "image_urls.txt";
	private List<String> images;

	public ImageUrlList() throws IOException {
		this.images = new ArrayList<String>();
		load();
	}

	public String random() {
		return images.get((int) Math.floor(Math.random() * images.size()));
	}

	private void load() throws IOException {
		images = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					APP.openFileInput(ImageUrlList.url_file)));
			String line = null;
			while ((line = reader.readLine()) != null) {
				images.add(line);
			}
		} catch (FileNotFoundException e) {

		}
	}

	public void save(List<String> images) throws IOException {
		this.images = images;
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					APP.openFileOutput(ImageUrlList.url_file,
							Context.MODE_PRIVATE)));

			for (String url : images) {
				writer.write(url);
				writer.newLine();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			App.Log.d("FileNotFound: " + ImageUrlList.url_file);
		}
	}
}
