package net.hisme.masaki.seiga_wallpaper.services;

import net.hisme.masaki.seiga_wallpaper.App;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Startup extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			App.Log.d("Boot Completed");
			if (App.li.change_wallpaper_enabled()) {
				App.li.start_wall_update_task();
			}
			if (App.li.clip_id_is_valid()) {
				App.li.start_clip_update_task();
			}
		}
	}

}
