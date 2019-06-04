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

import java.io.File;

public final class PluginDescription {
	protected PluginDescription() {}
	private final String name = "Java Cookies";
	private final String description = "Cookies library in Java.";
	private final String version = "0.0.3-SNAPSHOT";
	private final String github = "https://github.com/leothawne/JavaCookies";
	private final String current_path = new File(JavaCookies.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
	public final String getName() {
		return this.name;
	}
	public final String getDescription() {
		return this.description;
	}
	public final String getVersion() {
		return this.version;
	}
	public final String getGithub() {
		return this.github;
	}
	public final String getCurrentPath() {
		return this.current_path;
	}
}