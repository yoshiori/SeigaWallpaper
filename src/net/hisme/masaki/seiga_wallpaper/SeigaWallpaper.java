package net.hisme.masaki.seiga_wallpaper;

import android.app.Application;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;

public class SeigaWallpaper extends Application {
	protected static SeigaWallpaper self;
	private ImageUrlList image_url_list;

	public SeigaWallpaper() {
		super();
		SeigaWallpaper.self = SeigaWallpaper.this;
	}

	/**
	 * @return instance of Application
	 * @throws Exception
	 */
	public static SeigaWallpaper instance() throws Exception {
		return self;
	}

	/**
	 * @return clip id
	 */
	public int clip_id() {
		return Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(
				SeigaWallpaper.this).getString("clip_id", ""));
	}

	public ImageUrlList image_url_list() throws Exception {
		if (image_url_list == null) {
			this.image_url_list = new ImageUrlList();
		}
		return image_url_list;
	}

	/**
	 * @return Bitmap of random image
	 * @throws Exception
	 */
	public Bitmap random_image() throws Exception {
		return new Image(image_url_list().random()).bitmap();
	}
}
