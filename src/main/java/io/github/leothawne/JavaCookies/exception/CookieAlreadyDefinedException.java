package io.github.leothawne.JavaCookies.exception;

@SuppressWarnings("serial")
public final class CookieAlreadyDefinedException extends Exception {
	private String cookieId;
	public CookieAlreadyDefinedException(final String cookieId){
		super("Cookie already defined: " + cookieId);
		this.cookieId = cookieId;
	}
	public String getCookieId() {
		return cookieId;
	}
}