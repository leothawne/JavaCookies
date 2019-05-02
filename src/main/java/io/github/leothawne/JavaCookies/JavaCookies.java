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

import javax.swing.JOptionPane;

import io.github.leothawne.JavaCookies.exception.CookieAlreadyDefinedException;

public final class JavaCookies {
	private static final String vNumber = "0.0.2-SNAPSHOT";
	private static final String vDate = "02/05/2019 [BRT]";
	public static final void main(final String[] args) {
		if(args.length > 0) {
			if(args[0].equalsIgnoreCase("--version")) {
				System.out.println();
				System.out.println("Java Cookies v" + vNumber + " (" + vDate + ")");
				System.out.print("GitHub: https://github.com/leothawne/JavaCookies");
				System.out.println();
				System.exit(0);
			}
			if(args[0].equalsIgnoreCase("--debug")) {
				System.out.println();
				System.out.println("Java Cookies: Initiating testing sequence...");
				final JavaCookies program = new JavaCookies();
				final CookieManager manager = program.createCookieManager();
				int i = 1;
				while(i < 11) {
					System.out.println();
					System.out.println();
					System.out.println("Creating cookie " + i + "...");
					System.out.println();
					try {
						final Cookie cookie = manager.createCookie();
						cookie.setValue("This is the cookie " + i + "!");
						System.out.println("Cookie unique id: " + cookie.getUniqueId().toString());
						System.out.println("Cookie value: " + cookie.getValue().toString());
						System.out.println();
						System.out.println("Deleting cookie " + i + "...");
						manager.deleteCookie(cookie.getUniqueId());
					} catch (final CookieAlreadyDefinedException exception) {
						System.err.println("Could not create cookie " + i + ".");
						exception.printStackTrace();
					}
					i++;
				}
				System.out.println();
				System.out.println();
				System.out.print("Testing sequence completed!");
				System.out.println();
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
		return new CookieManager();
	}
}