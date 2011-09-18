package net.hisme.masaki.seiga_wallpaper.services;

import net.hisme.masaki.seiga_wallpaper.SeigaWallpaper;
import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.IBinder;

public class WallUpdater extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		SeigaWallpaper.Log.d("WallUpdater.start");
		try {
			WallpaperManager.getInstance(WallUpdater.this).setBitmap(
					SeigaWallpaper.instance().random_image());
		} catch (Exception e) {
			SeigaWallpaper.Log.d(String.format("Exception in wall updater: %s",
					e.getMessage()));
		}
		stopSelf();
	}
}
