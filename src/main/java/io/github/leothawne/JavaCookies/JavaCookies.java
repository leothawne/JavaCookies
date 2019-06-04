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

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

public final class JavaCookies {
	public static final void main(final String[] args) {
		if(args.length > 0) {
			final JavaCookies program = new JavaCookies();
			final PluginDescription pluginDescription = program.getPluginDescription();
			if(args[0].equalsIgnoreCase("--version")) {
				System.out.println();
				System.out.println(pluginDescription.getName() + " v" + pluginDescription.getVersion());
				System.out.println("\"" + pluginDescription.getDescription() + "\"");
				System.out.println();
				System.out.println("GitHub: \"" + pluginDescription.getGithub() + "\"");
				System.exit(0);
			}
			if(args[0].equalsIgnoreCase("--debug")) {
				System.out.println();
				System.out.println(pluginDescription.getName() + ": Initiating testing sequence...");
				final CookieManager cookieManager = program.createCookieManager();
				for(int i = 1; i <= 10000; i++) {
					System.out.println();
					System.out.println();
					System.out.println("Creating cookie " + i + "...");
					System.out.println();
					final Cookie cookie = cookieManager.createCookie("This is the cookie " + i + "!", 1);
					System.out.println("Cookie unique id: " + cookie.getUniqueId().toString());
					System.out.println("Cookie value: " + cookie.getValue().toString());
					System.out.println("Cookie timeout: " + cookie.getTimeout() + "s");
				}
				try {
					final SimpleDateFormat formatter = new SimpleDateFormat("HH_mm_ss-dd_MM_yyyy");
					cookieManager.saveToFile("debug-" + formatter.format(Calendar.getInstance().getTime()));
				} catch (final JCDBFileHandlerException exception) {
					exception.printStackTrace();
				}
				cookieManager.deleteAllCookies();
				System.out.println();
				System.out.println();
				System.out.println("Testing sequence completed!");
				System.exit(0);
			}
			if(args[0].equalsIgnoreCase("--extra-debug")) {
				System.out.println();
				System.out.println(pluginDescription.getName() + ": Initiating testing sequence...");
				final CookieManager cookieManager = program.createCookieManager();
				try {
					cookieManager.loadFromFile("debug");
					for(final Cookie cookie : cookieManager.getFormedCookies()) {
						System.out.println();
						System.out.println();
						System.out.println("Loading cookie...");
						System.out.println();
						System.out.println("Cookie unique id: " + cookie.getUniqueId().toString());
						System.out.println("Cookie value: " + cookie.getValue().toString());
						System.out.println("Cookie timeout: " + cookie.getTimeout() + "s");
					}
				} catch (final JCDBFileHandlerException exception) {
					exception.printStackTrace();
				}
				cookieManager.deleteAllCookies();
				System.out.println();
				System.out.println();
				System.out.println("Testing sequence completed!");
				System.exit(0);
			}
		}
		final String error = "This library is not intended to be used in this manner.";
		System.out.println();
		System.out.print(error);
		System.out.println();
		JOptionPane.showMessageDialog(null, error, "Java Cookies", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	public final CookieManager createCookieManager() {
		return new CookieManager(this);
	}
	public final PluginDescription getPluginDescription() {
		return new PluginDescription();
	}
}