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