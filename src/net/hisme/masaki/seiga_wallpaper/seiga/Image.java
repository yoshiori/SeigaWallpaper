package net.hisme.masaki.seiga_wallpaper.seiga;

import static net.hisme.masaki.seiga_wallpaper.App.APP;

import java.io.File;
import java.io.OutputStream;

import net.hisme.masaki.seiga_wallpaper.HTTPClient;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

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
		File path = APP.getFileStreamPath(filename);
		if (path.exists()) {
			bitmap = BitmapFactory.decodeStream(APP.openFileInput(filename));
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
		OutputStream output = APP.openFileOutput(filename(),
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
