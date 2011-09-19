package net.hisme.masaki.seiga_wallpaper.activities;

import net.hisme.masaki.seiga_wallpaper.R;
import net.hisme.masaki.seiga_wallpaper.SeigaWallpaper;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ClipActivity extends PreferenceActivity {

	private SharedPreferences.OnSharedPreferenceChangeListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref);

		listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
			@Override
			public void onSharedPreferenceChanged(
					SharedPreferences sharedPreferences, String key) {
				SeigaWallpaper.Log.d(String
						.format("update preference: %s", key));

				try {
					final SeigaWallpaper app = SeigaWallpaper.instance();

					if (key.compareTo("clip_id") == 0) {
						app.start_clip_update_task();
					}
					if (key.compareTo("enable_change_wallpaper") == 0) {
						if (sharedPreferences.getBoolean(key, false)) {
							app.start_wall_update_task();
						} else {
							app.stop_wall_update_task();
						}
					}
				} catch (Exception e) {
					SeigaWallpaper.Log.d("Exception in preference: "
							+ e.getMessage());
				}
			}
		};
	}

	public void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(listener);

	}

	public void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(listener);
	}

}
