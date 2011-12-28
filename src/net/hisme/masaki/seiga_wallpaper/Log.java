package net.hisme.masaki.seiga_wallpaper;

public class Log {
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