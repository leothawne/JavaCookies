package io.github.leothawne.JavaCookies;

import java.util.HashMap;
import java.util.UUID;

import io.github.leothawne.JavaCookies.exception.CookieAlreadyDefinedException;

public final class CookieManager {
	protected CookieManager() {}
	private final HashMap<UUID, Object> cookie = new HashMap<UUID, Object>();
	private final HashMap<String, Object> cookieDeprecated = new HashMap<String, Object>();
	public final UUID create(final UUID cookieId, final Object value) throws CookieAlreadyDefinedException {
		if(cookie.get(cookieId) == null) {
			cookie.put(cookieId, value);
			return cookieId;
		}
		throw new CookieAlreadyDefinedException(cookieId.toString());
	}
	public final UUID create(final Object value) throws CookieAlreadyDefinedException {
		UUID id = UUID.randomUUID();
		while(cookie.get(id) != null) {
			id = UUID.randomUUID();
		}
		return create(id, value);
	}
	/**
	 * 
	 * @deprecated
	 * 
	 */
	public final String create(final String cookieId, final Object value) throws CookieAlreadyDefinedException {
		if(cookieDeprecated.get(cookieId) == null) {
			cookieDeprecated.put(cookieId, value);
			return cookieId;
		}
		throw new CookieAlreadyDefinedException(cookieId);
	}
	public final boolean set(final UUID cookieId, final Object value) {
		cookie.put(cookieId, value);
		if(cookie.get(cookieId).equals(value)) return true;
		return false;
	}
	/**
	 * 
	 * @deprecated
	 * 
	 */
	public final boolean set(final String cookieId, final Object value) {
		cookieDeprecated.put(cookieId, value);
		if(cookieDeprecated.get(cookieId).equals(value)) return true;
		return false;
	}
	public final CookieData get(final UUID cookieId) {
		return new CookieData(cookie, cookieId);
	}
	/**
	 * 
	 * @deprecated
	 * 
	 */
	public final Object get(final String cookieId) {
		return cookieDeprecated.get(cookieId);
	}
	public final boolean kill(final UUID cookieId) {
		cookie.remove(cookieId);
		if(cookie.get(cookieId) == null) return true;
		return false;
	}
	/**
	 * 
	 * @deprecated
	 * 
	 */
	public final boolean kill(final String cookieId) {
		cookieDeprecated.remove(cookieId);
		if(cookieDeprecated.get(cookieId) == null) return true;
		return false;
	}
	public final boolean exists(final UUID cookieId) {
		if(cookie.get(cookieId) != null) return true;
		return false;
	}
	/**
	 * 
	 * @deprecated
	 * 
	 */
	public final boolean exists(final String cookieId) {
		if(cookieDeprecated.get(cookieId) != null) return true;
		return false;
	}
}