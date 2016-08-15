package com.davita.mcoe.drivers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.davita.mcoe.executionagent.BrowserAgent;
import com.davita.mcoe.executionagent.ExecutionAgent;
import com.davita.mcoe.utilities.Logg;
import com.davita.mcoe.utilities.Utilities;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class LocalDriver {

	private static final Logger LOGGER = Logg.createLogger();

	public WebDriver getDriver(ExecutionAgent executionAgent) {

		BrowserAgent browserAgent = (BrowserAgent) executionAgent;

		WebDriver driver = null;
		
		if ("internet explorer".equals(browserAgent.getName())) {
			LOGGER.info(Utilities.getCurrentThreadId()
					+ "**Internet Explorer Browser**");
			System.setProperty("webdriver.ie.driver",
					"src/main/resources/com/drivers/IEDriverServer.exe");
			DesiredCapabilities capabilities = WebCapabilities
					.setInternetExplorerCapability(browserAgent);
			LOGGER.info(Utilities.getCurrentThreadId()
					+ "Instantiating/Launching the Internet Explorer Browser");
			driver = new InternetExplorerDriver(capabilities);
		} else if ("firefox".equals(browserAgent.getName())) {
			LOGGER.info(Utilities.getCurrentThreadId() + "**FireFox Browser**");
			DesiredCapabilities capabilities = WebCapabilities
					.setFirefoxCapability(browserAgent);
			LOGGER.info(Utilities.getCurrentThreadId()
					+ "Instantiating/Launching the Firefox Browser");
			driver = new FirefoxDriver(capabilities);
			driver.manage().window().maximize();
		} else if ("chrome".equals(browserAgent.getName())) {
			LOGGER.info(Utilities.getCurrentThreadId() + "**Chrome Browser**");
			System.setProperty("webdriver.chrome.driver",
					"src/main/resources/com/drivers/chromedriver.exe");
			DesiredCapabilities capabilities = WebCapabilities
					.setChromeCapability(browserAgent);
			LOGGER.info(Utilities.getCurrentThreadId()
					+ "Instantiating/Launching the Chrome Browser");
			driver = new ChromeDriver(capabilities);
		}
		LOGGER.info(Utilities.getCurrentThreadId()
				+ "Returning the local instance of:" + driver.toString());
		return driver;
	}
}