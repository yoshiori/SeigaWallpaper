package net.hisme.masaki.seiga_wallpaper;

import java.io.File;
import java.io.OutputStream;

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
		Context context = SeigaWallpaper.instance();
		String filename = filename();
		File path = context.getFileStreamPath(filename);
		if (path.exists()) {
			bitmap = BitmapFactory.decodeStream(SeigaWallpaper.instance()
					.openFileInput(filename));
		} else {
			download();
		}
	}

	private String filename() {
		String[] path = url.split("/");
		return String.format("images_%s", path[path.length - 1]);
	}

	private void download() throws Exception {
		bitmap = BitmapFactory.decodeStream(HTTP_Client.get_stream(url));
		OutputStream output = SeigaWallpaper.instance().openFileOutput(
				filename(), Context.MODE_PRIVATE);
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
