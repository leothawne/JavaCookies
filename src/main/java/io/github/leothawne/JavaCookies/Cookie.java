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

import java.util.UUID;

public final class Cookie {
	private CookieManager cookieManager;
	private UUID cookieId;
	private Object value;
	private int timeout;
	protected Cookie(final CookieManager cookieManager, final UUID cookieId, final Object value, final int timeout) {
		this.cookieManager = cookieManager;
		this.cookieId = cookieId;
		this.value = value;
		this.timeout = timeout;
	}
	public final UUID getUniqueId() {
		if(this.cookieManager.getCookie(this.cookieId) != null) return this.cookieId;
		return null;
	}
	public final void setValue(final Object value) {
		if(this.cookieManager.getCookie(this.cookieId) != null) this.value = value;
	}
	public final Object getValue() {
		if(this.cookieManager.getCookie(this.cookieId) != null) return this.value;
		return null;
	}
	public final void setTimeout(int timeout) {
		if(this.cookieManager.getCookie(this.cookieId) != null) this.timeout = timeout;
	}
	public final int getTimeout() {
		if(this.cookieManager.getCookie(this.cookieId) != null) return this.timeout;
		return -1;
	}
}