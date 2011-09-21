package net.hisme.masaki.seiga_wallpaper.activities;

import net.hisme.masaki.seiga_wallpaper.R;
import net.hisme.masaki.seiga_wallpaper.App;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class SettingActivity extends PreferenceActivity {

	private SharedPreferences.OnSharedPreferenceChangeListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref);
		getPreferenceScreen().findPreference("clip_id").setSummary(
				App.li.raw_clip_id());
		getPreferenceScreen().findPreference("clip_id")
				.setOnPreferenceChangeListener(
						new Preference.OnPreferenceChangeListener() {
							@Override
							public boolean onPreferenceChange(
									Preference preference, Object newValue) {
								if (newValue.toString().matches("[1-9]\\d*"))
									return true;

								App.li.toast(R.string.invalid_clip_id_error);
								return false;
							}
						});

		listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
			@Override
			public void onSharedPreferenceChanged(
					SharedPreferences sharedPreferences, String key) {

				try {
					final App app = App.li;

					if (key.compareTo("clip_id") == 0) {
						SettingActivity.this.getPreferenceScreen()
								.findPreference(key).setSummary(
										sharedPreferences.getString(key, ""));
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
					App.Log.d("Exception in preference: " + e.getMessage());
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
