package net.hisme.masaki.seiga_wallpaper.seiga;

import java.io.File;
import java.io.OutputStream;

import net.hisme.masaki.seiga_wallpaper.HTTPClient;
import net.hisme.masaki.seiga_wallpaper.App;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;

public class Image {
	private String url;
	private Bitmap bitmap;

	/**
	 * create Image from URL
	 * 
	 * @param url
	 * @throws Exception
	 */
	public Image(String url) throws Exception {
		this.url = url;
		load();
	}

	private void load() throws Exception {
		String filename = filename();
		File path = App.li.getFileStreamPath(filename);
		if (path.exists()) {
			bitmap = BitmapFactory.decodeStream(App.li.openFileInput(filename));
		} else {
			download();
		}
	}

	private String filename() {
		String[] path = url.split("/");
		return String.format("images_%s", path[path.length - 1]);
	}

	private void download() throws Exception {
		bitmap = BitmapFactory.decodeStream(HTTPClient.getStream(url));
		OutputStream output = App.li.openFileOutput(filename(),
				Context.MODE_PRIVATE);
		bitmap.compress(CompressFormat.PNG, 100, output);
	}

	/**
	 * instance of Bitmap
	 * 
	 * @return Bitmap
	 */
	public Bitmap bitmap() {
		return bitmap;
	}
}
