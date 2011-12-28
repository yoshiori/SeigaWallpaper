package net.hisme.masaki.seiga_wallpaper;

import net.hisme.masaki.seiga_wallpaper.seiga.Image;
import net.hisme.masaki.seiga_wallpaper.seiga.ImageUrlList;
import net.hisme.masaki.seiga_wallpaper.services.ClipUpdater;
import net.hisme.masaki.seiga_wallpaper.services.WallUpdater;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class App extends Application {
	public static App APP;
	private ImageUrlList image_url_list;
	private PendingIntent clip_update_task;
	private PendingIntent wall_update_task;

	public App() {
		super();
		App.APP = App.this;
	}

	public void startWallUpdateTask() {
		AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
		if (wall_update_task == null) {
			wall_update_task = PendingIntent.getService(App.this, 0,
					new Intent(App.this, WallUpdater.class), 0);
		}
		Log.d("enable to update wallpaper");
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, 0, (long) 5 * 60 * 1000,
				wall_update_task);
	}

	public void stopWallUpdateTask() {
		if (wall_update_task != null) {
			AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
			Log.d("disable to update wallpaper");
			alarm.cancel(wall_update_task);
		}
	}

	public void startClipUpdateTask() {
		stopClipUpdateTask();
		AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

		checkClipId();

		if (clip_update_task == null) {
			clip_update_task = PendingIntent.getService(App.this, 0,
					new Intent(App.this, ClipUpdater.class), 0);
		}
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, 0,
				(long) 8 * 60 * 60 * 1000, clip_update_task);
	}

	public void stopClipUpdateTask() {
		if (clip_update_task != null) {
			AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
			Log.d("disable to update clip");
			alarm.cancel(clip_update_task);
		}
	}

	public String rawClipId() {
		return PreferenceManager.getDefaultSharedPreferences(App.this)
				.getString("clip_id", "");
	}

	/**
	 * @return clip id
	 */
	public int clipId() {
		try {
			int clip_id = Integer.parseInt(rawClipId());
			if (clip_id <= 0)
				throw new InvalidClipIdException();

			return clip_id;
		} catch (Exception e) {
			throw new InvalidClipIdException();
		}
	}

	public boolean checkClipId() {
		clipId();
		return true;
	}

	public boolean clipIdIsValid() {
		try {
			return checkClipId();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean changeWallpaperEnabled() {
		return PreferenceManager.getDefaultSharedPreferences(App.this)
				.getBoolean("enable_change_wallpaper", false);
	}

	public ImageUrlList imageUrlList() throws Exception {
		if (image_url_list == null) {
			this.image_url_list = new ImageUrlList();
		}
		return image_url_list;
	}

	public void toast(int res_id) {
		toast(res_id, Toast.LENGTH_SHORT);
	}

	public void toast(int res_id, int duration) {
		Toast.makeText(App.this, res_id, duration).show();
	}

	/**
	 * @return Bitmap of random image
	 * @throws Exception
	 */
	public Bitmap randomImage() throws Exception {
		return new Image(imageUrlList().random()).bitmap();
	}
}
