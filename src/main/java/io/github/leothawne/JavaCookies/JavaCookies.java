package io.github.leothawne.JavaCookies;

import javax.swing.JOptionPane;

public class JavaCookies {
	public static final void main(final String[] args) {
		final String error = "This library is not intended to be used in this manner.";
		System.out.print(error);
		JOptionPane.showMessageDialog(null, error, "Java Cookies", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	public final CookieManager createCookieManager() {
		return new CookieManager();
	}
}