package net.hisme.masaki.seiga_wallpaper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SeigaWallpaperActivity extends Activity {
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

	public void reload() {
		try {
			((ImageView) findViewById(R.id.image))
					.setImageBitmap(SeigaWallpaper.instance().random_image());
		} catch (Exception e) {

		}

	}
}