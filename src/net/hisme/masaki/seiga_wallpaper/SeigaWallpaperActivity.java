package net.hisme.masaki.seiga_wallpaper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class SeigaWallpaperActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		findViewById(R.id.get_html_button).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						try {
							Downloader
									.download(304308);
						} catch (Exception e) {

						}
					}
				});
	}
}