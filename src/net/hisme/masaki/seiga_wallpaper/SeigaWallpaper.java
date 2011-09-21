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

public class SeigaWallpaper extends Application {
	protected static SeigaWallpaper self;
	private ImageUrlList image_url_list;
	private PendingIntent clip_update_task;
	private PendingIntent wall_update_task;

	public SeigaWallpaper() {
		super();
		SeigaWallpaper.self = SeigaWallpaper.this;
	}

	public void start_wall_update_task() {
		AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
		if (wall_update_task == null) {
			wall_update_task = PendingIntent.getService(SeigaWallpaper.this, 0,
					new Intent(SeigaWallpaper.this, WallUpdater.class), 0);
		}
		Log.d("enable to update wallpaper");
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, 0, (long) 3 * 60 * 1000,
				wall_update_task);
	}

	public void stop_wall_update_task() {
		if (wall_update_task != null) {
			AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
			Log.d("disable to update wallpaper");
			alarm.cancel(wall_update_task);
		}
	}

	public void start_clip_update_task() {
		stop_clip_update_task();
		AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

		check_clip_id();

		if (clip_update_task == null) {
			clip_update_task = PendingIntent.getService(SeigaWallpaper.this, 0,
					new Intent(SeigaWallpaper.this, ClipUpdater.class), 0);
		}
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, 0,
				(long) 8 * 60 * 60 * 1000, clip_update_task);
	}

	public void stop_clip_update_task() {
		if (clip_update_task != null) {
			AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
			Log.d("disable to update clip");
			alarm.cancel(clip_update_task);
		}
	}

	/**
	 * @return instance of Application
	 */
	public static SeigaWallpaper instance() {
		return self;
	}

	public String raw_clip_id() {
		return PreferenceManager.getDefaultSharedPreferences(
				SeigaWallpaper.this).getString("clip_id", "");
	}

	/**
	 * @return clip id
	 */
	public int clip_id() {
		try {
			int clip_id = Integer.parseInt(raw_clip_id());
			if (clip_id <= 0)
				throw new InvalidClipId();

			return clip_id;
		} catch (Exception e) {
			throw new InvalidClipId();
		}
	}

	public boolean check_clip_id() {
		clip_id();
		return true;
	}

	public ImageUrlList image_url_list() throws Exception {
		if (image_url_list == null) {
			this.image_url_list = new ImageUrlList();
		}
		return image_url_list;
	}

	public void toast(int res_id) {
		toast(res_id, Toast.LENGTH_SHORT);
	}

	public void toast(int res_id, int duration) {
		Toast.makeText(SeigaWallpaper.this, res_id, duration).show();
	}

	/**
	 * @return Bitmap of random image
	 * @throws Exception
	 */
	public Bitmap random_image() throws Exception {
		return new Image(image_url_list().random()).bitmap();
	}

	public static class Log {
		private static final String tag = "SeigaWallpaper";

		public static void i(String str) {
			android.util.Log.i(tag, str);
		}

		public static void d(String str) {
			android.util.Log.d(tag, str);
		}

		public static void e(String str) {
			android.util.Log.e(tag, str);
		}

		public static void w(String str) {
			android.util.Log.w(tag, str);
		}
	}

	public static class InvalidClipId extends RuntimeException {
		private static final long serialVersionUID = 1L;

		@Override
		public String getMessage() {
			return String.format("Invalid ClipID: '%s'", SeigaWallpaper
					.instance().raw_clip_id());
		}
	}
}
