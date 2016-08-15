package com.davita.mcoe.drivers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.davita.mcoe.executionagent.BrowserAgent;
import com.davita.mcoe.executionagent.ExecutionAgent;
import com.davita.mcoe.property.reader.PropertyManager;
import com.davita.mcoe.utilities.Logg;
import com.davita.mcoe.utilities.Utilities;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class RemoteDriver {

	private static final Logger LOGGER = Logg.createLogger();
	private static final Properties FRAMEWORKPROPERTY = PropertyManager
			.loadFrameworkPropertyFile("framework.properties");
	private static final String HUBADDRESS = FRAMEWORKPROPERTY
			.getProperty("gridURL");
	private static final String HUBPORT = FRAMEWORKPROPERTY
			.getProperty("gridPort");
	private static final String TESTTYPE = FRAMEWORKPROPERTY
			.getProperty("testprop");
	
	public WebDriver getDriver(ExecutionAgent executionAgent) {
		
		System.out.println("------------------Prop---------------------- ="
				+ TESTTYPE);
		
		BrowserAgent browserAgent = (BrowserAgent) executionAgent;
		WebDriver driver = null;
		DesiredCapabilities capabilities = null;
		try {
			if ("internet explorer".equals(browserAgent.getName())) {
				LOGGER.info(Utilities.getCurrentThreadId()
						+ "**Remote Internet Explorer Browser**");
				capabilities = WebCapabilities
						.setInternetExplorerCapability(browserAgent);
			} else if ("firefox".equals(browserAgent.getName())) {
				LOGGER.info(Utilities.getCurrentThreadId()
						+ "**Remote FireFox Browser**");
				capabilities = WebCapabilities
						.setFirefoxCapability(browserAgent);
			} else if ("chrome".equals(browserAgent.getName())) {
				LOGGER.info(Utilities.getCurrentThreadId()
						+ "**Remote Chrome Browser**");
				System.setProperty("webdriver.chrome.driver",
						"src/main/resources/com/drivers/chromedriver.exe");
				capabilities = WebCapabilities
						.setChromeCapability(browserAgent);
			}
			driver = new RemoteWebDriver(new URL("http://" + HUBADDRESS + ":"
					+ HUBPORT + "/wd/hub"), capabilities);
			LOGGER.info(Utilities.getCurrentThreadId()
					+ "Returning the remote instance of:" + driver);
		} catch (MalformedURLException me) {
			LOGGER.info(
					"MalformedURLException in the getDriver() method of the RemoteDriver class",
					me);
		}
		return driver;
	}
}
