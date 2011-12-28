package net.hisme.masaki.seiga_wallpaper;

import static net.hisme.masaki.seiga_wallpaper.App.APP;

public class InvalidClipIdException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return String.format("Invalid ClipID: '%s'", APP.rawClipId());
	}
}
