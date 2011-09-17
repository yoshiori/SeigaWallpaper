package net.hisme.masaki.seiga_wallpaper;

import android.app.Application;
import android.content.Context;

public class SeigaWallpaper extends Application {
	protected static SeigaWallpaper self;

	public SeigaWallpaper() {
		super();
		SeigaWallpaper.self = SeigaWallpaper.this;
	}

	public static Context context() throws Exception {
		return self;
	}
}
