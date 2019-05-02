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

public final class Cookie {
	private UUID cookieId;
	private HashMap<UUID, Object> cookie;
	protected Cookie(final HashMap<UUID, Object> cookie, final UUID cookieId, Object value) {
		cookie.put(cookieId, value);
		this.cookieId = cookieId;
		this.cookie = cookie;
	}
	public UUID getUniqueId() {
		return cookieId;
	}
	public final boolean setValue(final Object value) {
		cookie.put(cookieId, value);
		if(cookie.get(cookieId).equals(value)) return true;
		return false;
	}
	public final Object getValue() {
		return cookie.get(getUniqueId());
	}
}