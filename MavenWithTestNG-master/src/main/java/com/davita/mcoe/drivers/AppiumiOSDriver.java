package com.davita.mcoe.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;

import com.davita.mcoe.exceptions.HostNotReachableException;
import com.davita.mcoe.exceptions.ResponceCodeMismatchException;
import com.davita.mcoe.executionagent.ExecutionAgent;
import com.davita.mcoe.executionagent.MobileAgent;
import com.davita.mcoe.installation.monitor.AppMonitor;
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
public class AppiumiOSDriver {
	private static final Properties FRAMEWORKPROPERTY = PropertyManager
			.loadFrameworkPropertyFile("framework.properties");
	private static final Logger LOGGER = Logg.createLogger();
	private static final String APPIUMSERVER = FRAMEWORKPROPERTY.getProperty("appiumServerIP");
	private static final String APPIUMPORT = FRAMEWORKPROPERTY.getProperty("appiumServerPort");
	private static final String DEVICETYPE = FRAMEWORKPROPERTY.getProperty("deviceType");
	private static final String TESTTYPE = FRAMEWORKPROPERTY.getProperty("testprop");

	@SuppressWarnings("rawtypes")
	public AppiumDriver<MobileElement> getDriver(ExecutionAgent executionAgent, AppMonitor monitor) {
		System.out.println("------------------Prop---------------------- =" + TESTTYPE);
		AppiumDriver<MobileElement> driver = null;
		MobileAgent mobileAgent = (MobileAgent) executionAgent;
		DesiredCapabilities capabilities = MobileCapabilities.setAppiumNativeAppCapability(DEVICETYPE, mobileAgent, monitor);
		try {
			LOGGER.info(Utilities.getCurrentThreadId() + "**Mobile Driver**");
			LOGGER.info(Utilities.getCurrentThreadId() + "Instantiating the Appium Driver");
			if ("emulator".equals(DEVICETYPE) || "real".equals(DEVICETYPE)) {
				LOGGER.info(Utilities.getCurrentThreadId() + "DEVICE TYPE Parameter is set to Emulator or Real Key");
				LOGGER.info(Utilities.getCurrentThreadId() + "APPIUM SERVER:" + APPIUMSERVER);
				LOGGER.info(Utilities.getCurrentThreadId() + "APPIUM PORT:" + APPIUMPORT);
				String appiumUrl = "http://" + APPIUMSERVER + ":" + APPIUMPORT + "/wd/hub";
				try {
					if (HTTPClient.isHostReachable(appiumUrl + "/status")) {
						driver = new IOSDriver<MobileElement>(new URL(appiumUrl), capabilities);
					} else {
						LOGGER.error(Utilities.getCurrentThreadId() + "Response Code Mismatch for the Appium URL."
								+ "Exiting the system");
						System.exit(1);
					}
				} catch (HostNotReachableException host) {
					HostNotReachableException hostNotReachableException = new HostNotReachableException(
							"Check if the Appium Server is Up and Running");
					LOGGER.error(Utilities.getCurrentThreadId() + "Exception in intializing the iOS Appium Driver."
							+ "Exiting the system", hostNotReachableException);
					System.exit(1);
				}
			} else {
				LOGGER.info(Utilities.getCurrentThreadId() + "DEVICE TYPE Parameter is neither Emulator or Real Key");
				LOGGER.info(Utilities.getCurrentThreadId() + "Switching to Mobile Cloud");
				String host = "davita.perfectomobile.com";
				// String host = "mobilecloudexpress.davita.com";//
				// http://mobilecloudexpress.davita.com/nexperience/
				String url = "https://" + host + "/nexperience/perfectomobile/wd/hub";
				driver = new IOSDriver<MobileElement>(new URL(url), capabilities);
			}
			LOGGER.info(Utilities.getCurrentThreadId() + "Returning the mobile instance of:" + driver);
		} catch (MalformedURLException me) {
			LOGGER.info("MalformedURLException in the getDriver() method of the MobileDriver class", me);
		}
		return driver;
	}
}