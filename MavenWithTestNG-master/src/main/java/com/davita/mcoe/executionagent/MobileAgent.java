package com.davita.mcoe.executionagent;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class MobileAgent extends ExecutionAgent {
	private String network;
	private String maufacturer;
	private String deviceName;
	private String deviceType;
	private String deviceId;
	private String platformVersion;
	private String username;
	private String password;
	private String appPackage;
	private String platformName;
	private String appPath;

	public MobileAgent(String network, String maufacturer, String deviceName,
			String deviceType, String deviceId, String platformVersion,
			String platformName, String username, String password,
			String appPackage, String appPath) {
		this.network = network;
		this.maufacturer = maufacturer;
		this.deviceName = deviceName;
		this.deviceType = deviceType;
		this.deviceId = deviceId;
		this.platformVersion = platformVersion;
		this.platformName = platformName;
		this.username = username;
		this.password = password;
		this.appPackage = appPackage;
		this.appPath = appPath;
	}

	public String getAppPath() {
		return appPath;
	}

	public String getAppPackage() {
		return appPackage;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPlatformVersion() {
		return platformVersion;
	}

	public String getNetwork() {
		return network;
	}

	public String getMaufacturer() {
		return maufacturer;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public String getPlatform() {
		return platformName;
	}
}