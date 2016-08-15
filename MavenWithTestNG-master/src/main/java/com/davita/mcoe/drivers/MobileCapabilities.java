package com.davita.mcoe.drivers;

import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;

import com.davita.mcoe.exceptions.HostNotReachableException;
import com.davita.mcoe.exceptions.ResponceCodeMismatchException;
import com.davita.mcoe.executionagent.MobileAgent;
import com.davita.mcoe.extendable.locator.AppConfig;
import com.davita.mcoe.installation.monitor.AppMonitor;
import com.davita.mcoe.installation.monitor.DavitaHomeMonitor;
import com.davita.mcoe.installation.monitor.DavitaVHMonitor;
import com.davita.mcoe.property.reader.PropertyManager;
import com.davita.mcoe.services.HTTPClient;
import com.davita.mcoe.utilities.Logg;
import com.davita.mcoe.utilities.Utilities;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class MobileCapabilities {

	private static DesiredCapabilities capabilities;
	private static final String APKPATH = "src/test/resources/com/apk";
	private static final Properties APPLICATIONPROPERTY = PropertyManager
			.loadApplicationPropertyFile("application.properties");
	private static String downloadableAppURLKey;
	private static final Logger LOGGER = Logg.createLogger();

	private MobileCapabilities() {
	}

	public static DesiredCapabilities setAppiumNativeAppCapability(String deviceType, MobileAgent mobileAgent, AppMonitor monitor) {
		File appDir = new File(APKPATH);

		capabilities = new DesiredCapabilities();

		capabilities.setCapability("automationName", "appium");
		// https://github.com/appium/appium/issues/4178
		// capabilities.setCapability("waitForAppScript",
		// "$.delay(5000);$.acceptAlert()");
		if ("iOS".equals(AppConfig.getPlatform())) {
			if (AppConfig.getAppName().equals("HOME")) {
				if (AppConfig.getAppEnvirnonment().equals("DEV")) {
					downloadableAppURLKey = "homeIoSDev";
				} else if (AppConfig.getAppEnvirnonment().equals("QA")) {
					downloadableAppURLKey = "homeIoSQA";
				} else if (AppConfig.getAppEnvirnonment().equals("UAT")) {
					downloadableAppURLKey = "homeIoSUAT";
				}
			} else if (AppConfig.getAppName().equals("INDIA")) {
				if (AppConfig.getAppEnvirnonment().equals("DEV")) {
					downloadableAppURLKey = "indiaIoSDev";
				} else if (AppConfig.getAppEnvirnonment().equals("QA")) {
					downloadableAppURLKey = "indiaIoSQA";
				} else if (AppConfig.getAppEnvirnonment().equals("UAT")) {
					downloadableAppURLKey = "indiaIoSUAT";
				}

			} else if (AppConfig.getAppName().equals("VH")) {
				if (AppConfig.getAppEnvirnonment().equals("DEV")) {
					downloadableAppURLKey = "vhIoSDev";
				} else if (AppConfig.getAppEnvirnonment().equals("QA")) {
					downloadableAppURLKey = "vhIoSQA";
				} else if (AppConfig.getAppEnvirnonment().equals("UAT")) {
					downloadableAppURLKey = "vhIoSUAT";
				}
			}
		} else if ("Android".equals(AppConfig.getPlatform())) {
			if (AppConfig.getAppName().equals("HOME")) {
				if (AppConfig.getAppEnvirnonment().equals("DEV")) {
					downloadableAppURLKey = "homeAndroidDev";
				} else if (AppConfig.getAppEnvirnonment().equals("QA")) {
					downloadableAppURLKey = "homeAndroidQA";
				} else if (AppConfig.getAppEnvirnonment().equals("UAT")) {
					downloadableAppURLKey = "homeAndroidUAT";
				}
			} else if (AppConfig.getAppName().equals("INDIA")) {
				if (AppConfig.getAppEnvirnonment().equals("DEV")) {
					downloadableAppURLKey = "indiaAndroidDev";
				} else if (AppConfig.getAppEnvirnonment().equals("QA")) {
					downloadableAppURLKey = "indiaAndroidQA";
				} else if (AppConfig.getAppEnvirnonment().equals("UAT")) {
					downloadableAppURLKey = "indiaAndroidUAT";
				}

			} else if (AppConfig.getAppName().equals("VH")) {
				if (AppConfig.getAppEnvirnonment().equals("DEV")) {
					downloadableAppURLKey = "vhAndroidDev";
				} else if (AppConfig.getAppEnvirnonment().equals("QA")) {
					downloadableAppURLKey = "vhAndroidQA";
				} else if (AppConfig.getAppEnvirnonment().equals("UAT")) {
					downloadableAppURLKey = "vhAndroidUAT";
				}
			}
		}

		String appToDownload = APPLICATIONPROPERTY.getProperty(downloadableAppURLKey);
		boolean bool = monitor.getAppInstallationStateForEnv(AppConfig.getPlatform(), AppConfig.getAppEnvirnonment());
		// distinguish between app or http url being passed to
		// downloadableAppURLKey
		if (appToDownload.contains("http") && !bool) {
			try {
				if (HTTPClient.isHostReachable(appToDownload)) {
					capabilities.setCapability(MobileCapabilityType.APP,appToDownload);
					monitor.changeAppInstallationStateForEnv(AppConfig.getPlatform(), AppConfig.getAppEnvirnonment(), true);
				} else {
					throw new ResponceCodeMismatchException(
							"Response Code is not in the range of 200." + "Check for any connectivity issues");
				}
			} catch (HostNotReachableException hostException) {
				LOGGER.error("Exception in verifying the downloadable app url. Exiting the System", hostException);
				System.exit(1);
			}
		} else if (bool) {
			capabilities.setCapability("bundleId", monitor.getBundleId());
		} else if (appToDownload.equals("")) {
			throw new RuntimeException("URL or App name not found in the properties file");
		} else {
			File app = new File(appDir, appToDownload);
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		}

		if ("emulator".equals(deviceType)) {
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, mobileAgent.getDeviceName());
			capabilities.setCapability(CapabilityType.PLATFORM, mobileAgent.getPlatform());
			capabilities.setCapability("appium-version", "1.0");
			// capabilities.setCapability("fullReset", true);
			// capabilities.setCapability("autoDismissAlerts", true);
		} else if ("real".equals(deviceType)) {
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, mobileAgent.getDeviceName());
			capabilities.setCapability(MobileCapabilityType.UDID, mobileAgent.getDeviceId());
			capabilities.setCapability("appium-version", "1.0");
			capabilities.setCapability(CapabilityType.PLATFORM, mobileAgent.getPlatform());
			capabilities.setCapability("noReset", true);
			capabilities.setCapability("fullReset", false);
			// capabilities.setCapability("autoDismissAlerts", true);
			LOGGER.info(Utilities.getCurrentThreadId() + "Running Mobile Device with the following Capabilities");
			LOGGER.info(Utilities.getCurrentThreadId() + capabilities);
		} else {
			capabilities.setCapability("user", mobileAgent.getUsername());
			capabilities.setCapability("password", mobileAgent.getPassword());
			capabilities.setCapability("platformVersion", mobileAgent.getPlatformVersion());
			capabilities.setCapability("network", mobileAgent.getNetwork());
			capabilities.setCapability("manufacturer", mobileAgent.getMaufacturer());
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, mobileAgent.getDeviceName());
			capabilities.setCapability(MobileCapabilityType.APP, "PUBLIC:Samples/base.apk");
			capabilities.setCapability("appPackage", mobileAgent.getAppPackage());
		}

		return capabilities;
	}

	public static DesiredCapabilities setAppiumBrowserCapability() {
		capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		return capabilities;
	}
}
