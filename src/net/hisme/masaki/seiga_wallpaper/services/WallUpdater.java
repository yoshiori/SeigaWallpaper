package net.hisme.masaki.seiga_wallpaper.services;

import static net.hisme.masaki.seiga_wallpaper.App.APP;

import net.hisme.masaki.seiga_wallpaper.App;
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
		App.Log.d("WallUpdater.start");
		try {
			WallpaperManager.getInstance(WallUpdater.this).setBitmap(
					APP.randomImage());
		} catch (Exception e) {
			App.Log.d(String.format("Exception in wall updater: %s", e
					.getMessage()));
		}
		stopSelf();
	}
}
