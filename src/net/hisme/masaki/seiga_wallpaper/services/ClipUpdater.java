package net.hisme.masaki.seiga_wallpaper.services;

import net.hisme.masaki.seiga_wallpaper.Log;
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
		Log.d("ClipUpdater.start");
		try {
			new Clip().save();
		} catch (Exception e) {
			Log.d(String.format("Exception in clip_updater: %s",
					e.getMessage()));
		}
		stopSelf();
	}
}
