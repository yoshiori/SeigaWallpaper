package net.hisme.masaki.seiga_wallpaper.services;

import net.hisme.masaki.seiga_wallpaper.SeigaWallpaper;
import net.hisme.masaki.seiga_wallpaper.seiga.Clip;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ClipUpdater extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		SeigaWallpaper.Log.d("ClipUpdater.start");
		try {
			new Clip().save();
		} catch (Exception e) {
			SeigaWallpaper.Log.d(String.format("Exception in clip_updater: %s",
					e.getMessage()));
		}
		stopSelf();
	}
}
