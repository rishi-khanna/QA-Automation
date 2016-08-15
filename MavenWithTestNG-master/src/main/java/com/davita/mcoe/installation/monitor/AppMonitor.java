package com.davita.mcoe.installation.monitor;

public class AppMonitor {

	private boolean isiOSDevEnvAppInstalled;
	private boolean isiOSQAEnvAppInstalled;
	private boolean isiOSUATEnvAppInstalled;
	private boolean isAndroidDevEnvAppInstalled;
	private boolean isiAndroidQAEnvAppInstalled;
	private boolean isAndroidUATEnvAppInstalled;

	private String platform;
	protected String env;

	public AppMonitor(String platform, String env) {
		this.platform = platform;
		this.env = env;
	}

	public void changeAppInstallationStateForEnv(String platform, String env, boolean state) {
		if ("iOS".equals(platform)) {
			if ("DEV".equals(env)) {
				isiOSDevEnvAppInstalled = state;
			} else if ("QA".equals(env)) {
				isiOSQAEnvAppInstalled = state;
			} else {
				isiOSUATEnvAppInstalled = state;
			}
		} else {
			if ("DEV".equals(env)) {
				isAndroidDevEnvAppInstalled = state;
			} else if ("QA".equals(env)) {
				isiAndroidQAEnvAppInstalled = state;
			} else {
				isAndroidUATEnvAppInstalled = state;
			}
		}
	}

	public boolean getAppInstallationStateForEnv(String platform, String env) {
		if ("iOS".equals(platform)) {
			if ("DEV".equals(env)) {
				return isiOSDevEnvAppInstalled;
			} else if ("QA".equals(env)) {
				return isiOSQAEnvAppInstalled;
			} else {
				return isiOSUATEnvAppInstalled;
			}
		} else {
			if ("DEV".equals(env)) {
				return isAndroidDevEnvAppInstalled;
			} else if ("QA".equals(env)) {
				return isiAndroidQAEnvAppInstalled;
			} else {
				return isAndroidUATEnvAppInstalled;
			}
		}
	}

	public String getBundleId() {
		return null;
	}

	public String getAppActivity() {
		return null;
	}

	public String getAppPackage() {
		return null;
	}

}
