/*
 * Copyright (C) 2019 Murilo Amaral Nappi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.leothawne.JavaCookies;

import java.util.HashMap;
import java.util.UUID;

import io.github.leothawne.JavaCookies.exception.CookieAlreadyDefinedException;

public final class CookieManager {
	protected CookieManager() {}
	private final HashMap<UUID, Object> cookie = new HashMap<UUID, Object>();
	public final Cookie createCookie(final UUID cookieId, final Object value) throws CookieAlreadyDefinedException {
		if(cookie.get(cookieId) == null) {
			return new Cookie(cookie, cookieId, value);
		}
		throw new CookieAlreadyDefinedException(cookieId.toString());
	}
	public final Cookie createCookie(final UUID cookieId) throws CookieAlreadyDefinedException {
		return createCookie(cookieId, null);
	}
	public final Cookie createCookie() throws CookieAlreadyDefinedException {
		UUID cookieId = UUID.randomUUID();
		while(cookie.get(cookieId) != null) {
			cookieId = UUID.randomUUID();
		}
		return createCookie(cookieId, null);
	}
	public final boolean deleteCookie(final UUID cookieId) {
		cookie.remove(cookieId);
		if(cookie.get(cookieId) == null) return true;
		return false;
	}
	public final boolean alreadyExists(final UUID cookieId) {
		if(cookie.get(cookieId) != null) return true;
		return false;
	}
}