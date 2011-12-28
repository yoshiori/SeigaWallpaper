package net.hisme.masaki.seiga_wallpaper.services;

import static net.hisme.masaki.seiga_wallpaper.App.APP;

import net.hisme.masaki.seiga_wallpaper.App;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Startup extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			App.Log.d("Boot Completed");
			if (APP.changeWallpaperEnabled()) {
				APP.startWallUpdateTask();
			}
			if (APP.clipIdIsValid()) {
				APP.startClipUpdateTask();
			}
		}
	}

}
