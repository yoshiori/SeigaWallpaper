package net.hisme.masaki.seiga_wallpaper.activities;

import static net.hisme.masaki.seiga_wallpaper.App.APP;
import net.hisme.masaki.seiga_wallpaper.InvalidClipIdException;
import net.hisme.masaki.seiga_wallpaper.Log;
import net.hisme.masaki.seiga_wallpaper.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		reload();

		findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				reload();
			}
		});

		if (!APP.clipIdIsValid()) {
			APP.toast(R.string.startup);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_config:
			startActivity(new Intent(MainActivity.this, SettingActivity.class));
			return true;
		case R.id.menu_reload:
			try {
				APP.startClipUpdateTask();
			} catch (InvalidClipIdException e) {
				APP.toast(R.string.invalid_clip_id_error);
			} catch (Exception e) {
				Log.d(String.format("Exception in menu_reload(): %s", e
						.getMessage()));
			}
			return true;
		case R.id.menu_help:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri
					.parse(getString(R.string.api_url)
							+ getString(R.string.help_url))));
			return true;
		case R.id.menu_info:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri
					.parse(getString(R.string.api_url)
							+ getString(R.string.info_url))));
			return true;
		}
		return false;
	}

	public void reload() {
		final Handler handler = new Handler();
		new Thread() {
			public void run() {
				handler.post(new Runnable() {
					public void run() {
						try {
							((ImageView) MainActivity.this
									.findViewById(R.id.image))
									.setImageBitmap(APP.randomImage());
						} catch (Exception e) {
							Log.d(String.format("Exception in reload: %s",
									e.getMessage()));
						}
					}
				});
			}
		}.start();
	}
}