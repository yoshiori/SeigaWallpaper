package net.hisme.masaki.seiga_wallpaper;

import android.app.Application;
import android.graphics.Bitmap;

public class SeigaWallpaper extends Application {
	protected static SeigaWallpaper self;
	private ImageUrlList image_url_list;

	public SeigaWallpaper() {
		super();
		SeigaWallpaper.self = SeigaWallpaper.this;
	}

	public static SeigaWallpaper instance() throws Exception {
		return self;
	}

	public int clip_id() {
		return getSharedPreferences("seiga_clip", Application.MODE_PRIVATE)
				.getInt("id", 0);
	}

	public ImageUrlList image_url_list() throws Exception {
		if (image_url_list == null) {
			this.image_url_list = new ImageUrlList();
		}
		return image_url_list;
	}

	public Bitmap random_image() throws Exception {
		return new Image(image_url_list().random()).bitmap();
	}
}
