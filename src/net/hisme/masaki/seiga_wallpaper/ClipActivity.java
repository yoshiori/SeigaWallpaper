package net.hisme.masaki.seiga_wallpaper;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ClipActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref);
	}
}
