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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ConcurrentModificationException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public final class CookieManager {
	private JavaCookies instance;
	protected CookieManager(final JavaCookies instance) {
		this.instance = instance;
		final CookieManager cookieManager = this;
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public final void run() {
				for(final UUID cookieId : cookies.keySet()) {
					final Cookie cookie = cookieManager.getCookie(cookieId);
					if(cookie.getTimeout() == 0) {
						cookieManager.deleteCookie(cookie.getUniqueId());
					} else if(cookie.getTimeout() > 0) {
						cookie.setTimeout(cookie.getTimeout() - 1);
					}
				}
			}
		}, 1000);
	}
	private final LinkedHashMap<UUID, Cookie> cookies = new LinkedHashMap<UUID, Cookie>();
	private final Cookie createCookie(final UUID cookieId, final Object value, final int timeout) {
		final Cookie cookie = new Cookie(this, cookieId, value, timeout);
		this.cookies.put(cookieId, cookie);
		return cookie;
	}
	public final Cookie createCookie(final Object value, final int timeout) {
		UUID cookieId = UUID.randomUUID();
		while(this.alreadyExists(cookieId)) {
			cookieId = UUID.randomUUID();
		}
		return this.createCookie(cookieId, value, timeout);
	}
	public final Cookie createCookie(final Object value) {
		return this.createCookie(value, -1);
	}
	public final LinkedList<Cookie> getFormedCookies(){
		final LinkedList<Cookie> list = new LinkedList<Cookie>();
		for(final UUID cookieId : this.cookies.keySet()) {
			list.add(this.getCookie(cookieId));
		}
		return list;
	}
	public final void deleteCookie(final UUID cookieId) {
		this.cookies.remove(cookieId);
	}
	public final void deleteAllCookies() {
		try {
			for(final UUID cookieId : this.cookies.keySet()) {
				this.deleteCookie(cookieId);
			}
		} catch(final ConcurrentModificationException exception) {
			
		}
	}
	public final Cookie getCookie(final UUID cookieId) {
		return this.cookies.get(cookieId);
	}
	public final boolean alreadyExists(final UUID cookieId) {
		return this.getCookie(cookieId) != null;
	}
	public final LinkedList<UUID> getCookies(){
		final LinkedList<UUID> cookies = new LinkedList<UUID>();
		for(final UUID cookieId : this.cookies.keySet()) {
			final Cookie cookie = this.getCookie(cookieId);
			cookies.add(cookie.getUniqueId());
		}
		return cookies;
	}
	public final boolean saveToFile(final String fileName) throws JCDBFileHandlerException {
		final File file = new File(this.instance.getPluginDescription().getCurrentPath(), fileName + ".jcdb");
		if(!file.exists()) {
			String contents = "";
			for(final UUID cookieId : this.cookies.keySet()) {
				final Cookie cookie = this.getCookie(cookieId);
				contents = contents + cookieId.toString() + ">=!=<" + cookie.getValue().toString() + "<=!=>";
			}
			try {
				final FileWriter writer = new FileWriter(file);
				final BufferedWriter buffer = new BufferedWriter(writer);
				buffer.write(contents);
				buffer.close();
				writer.close();
				return true;
			} catch (final IOException exception) {
				throw new JCDBFileHandlerException("Unable to save to file.", exception);
			}
		} else throw new JCDBFileHandlerException(fileName + ".jcdb already exists.");
	}
	public final boolean loadFromFile(final String fileName) throws JCDBFileHandlerException {
		final File file = new File(this.instance.getPluginDescription().getCurrentPath(), fileName + ".jcdb");
		if(file.exists()) {
			try {
				final FileInputStream input = new FileInputStream(file.getAbsolutePath());
				final BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				String content = reader.readLine();
				final StringBuilder builder = new StringBuilder();
				while(content != null) {
					builder.append(content);
					content = reader.readLine();
				}
				reader.close();
				input.close();
				for(final String cookies : builder.toString().split("<=!=>")) {
					final String[] args = cookies.split(">=!=<");
					this.createCookie(UUID.fromString(args[0]), (Object) args[1], -1);
				}
				return true;
			} catch (final IOException exception) {
				throw new JCDBFileHandlerException("FileInputStream || BufferedReader: Unable to close.", exception);
			}
		} else throw new JCDBFileHandlerException(fileName + ".jcdb does not exist.");
	}
}