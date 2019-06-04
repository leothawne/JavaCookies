package io.github.leothawne.JavaCookies;

public final class JCDBFileHandlerException extends Exception {
	private static final long serialVersionUID = 1L;
	protected JCDBFileHandlerException(final String message) {
		super(message);
	}
	protected JCDBFileHandlerException(final String message, final Throwable error) {
		super(message, error);
	}
}