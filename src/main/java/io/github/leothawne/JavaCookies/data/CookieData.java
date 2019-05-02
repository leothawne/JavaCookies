package io.github.leothawne.JavaCookies.data;

import java.util.HashMap;
import java.util.UUID;

public class CookieData {
	private final HashMap<UUID, Object> cookie;
	private final UUID cookieId;
	public CookieData(final HashMap<UUID, Object> cookie, final UUID cookieId) {
		this.cookie = cookie;
		this.cookieId = cookieId;
	}
	public final Object getRawObject() {
		return cookie.get(cookieId);
	}
	public final String toString() {
		return (String) getRawObject();
	}
	public final int toInt() {
		return (int) getRawObject();
	}
	public final boolean toBoolean() {
		return (boolean) getRawObject();
	}
	public final UUID toUUID() {
		return (UUID) getRawObject();
	}
	public final double toDouble() {
		return (double) getRawObject();
	}
}