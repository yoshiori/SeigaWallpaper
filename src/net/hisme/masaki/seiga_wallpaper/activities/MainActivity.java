package net.hisme.masaki.seiga_wallpaper.activities;

import net.hisme.masaki.seiga_wallpaper.R;
import net.hisme.masaki.seiga_wallpaper.SeigaWallpaper;
import android.app.Activity;
import android.content.Intent;
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
			startActivity(new Intent(MainActivity.this, ClipActivity.class));
			return true;
		case R.id.menu_reload:
			try {
				SeigaWallpaper.instance().start_clip_update_task();
			} catch (SeigaWallpaper.InvalidClipId e) {
				SeigaWallpaper.instance().toast(R.string.invalid_clip_id_error);
			} catch (Exception e) {
				SeigaWallpaper.Log.d(String.format(
						"Exception in menu_reload(): %s", e.getMessage()));
			}
			return true;
		case R.id.menu_help:
			// startActivity(new Intent(MainActivity.this, HelpActivity.class));
			return true;
		case R.id.menu_about:
			// startActivity(new Intent(MainActivity.this,
			// AboutActivity.class));
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
									.setImageBitmap(SeigaWallpaper.instance()
											.random_image());
						} catch (Exception e) {
							SeigaWallpaper.Log.d(String.format(
									"Exception in reload: %s", e.getMessage()));
						}
					}
				});
			}
		}.start();
	}
}