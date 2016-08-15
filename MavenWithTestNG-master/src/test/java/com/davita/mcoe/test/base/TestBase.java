package com.davita.mcoe.test.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.davita.mcoe.drivers.AppiumAndroidDriver;
import com.davita.mcoe.drivers.AppiumiOSDriver;
import com.davita.mcoe.drivers.LocalDriver;
import com.davita.mcoe.drivers.RemoteDriver;
import com.davita.mcoe.executionagent.BrowserAgent;
import com.davita.mcoe.executionagent.ExecutionAgent;
import com.davita.mcoe.executionagent.MobileAgent;
import com.davita.mcoe.extendable.locator.AppConfig;
import com.davita.mcoe.installation.monitor.AppMonitor;
import com.davita.mcoe.installation.monitor.DavitaHomeMonitor;
import com.davita.mcoe.installation.monitor.DavitaIndiaMonitor;
import com.davita.mcoe.installation.monitor.DavitaVHMonitor;
import com.davita.mcoe.property.reader.PropertyManager;
import com.davita.mcoe.utilities.DateAndTime;
import com.davita.mcoe.utilities.Logg;
import com.davita.mcoe.utilities.ReadExcel;
import com.davita.mcoe.utilities.Utilities;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.Augmenter;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class TestBase {
	protected static final Properties frameworkProperty = PropertyManager
			.loadFrameworkPropertyFile("framework.properties");
	protected static final Logger log = Logg.createLogger();
	protected final static Utilities util = new Utilities();
	protected static String[][] strorage = null;
	protected final static Properties applicationProperty = PropertyManager
			.loadApplicationPropertyFile("application.properties");
	private ITestContext context;
	private AppMonitor monitor;

	@DataProvider(name = "ReadExcel")
	public String[][] readDataFromExcel(Method m) throws Exception {
		log.info(Utilities.getCurrentThreadId() + "Data Provider: Read Excel");
		log.info(Utilities.getCurrentThreadId() + "Data Provider: Running for Method: " + m.getName());
		if ("smokeTest3".equals(m.getName())) {
			strorage = ReadExcel.readTestData("Patients");
			log.info(Utilities.getCurrentThreadId()
					+ "Data Provider: Retrieved data from the Patients Sheet of Test Data Excel");
		} else if ("".equals(m.getName())) {
			strorage = ReadExcel.readTestData("Sheet2");
		} else {
			log.info(Utilities.getCurrentThreadId()
					+ "NO MATCHING METHOD FOUND. PLEASE CHECK THE METHOD NAME IN THE DATA PROVIDER");
		}
		return strorage;
	}

	protected void logErrorMessage(Throwable ex) {
		StringWriter stw = new StringWriter();
		PrintWriter pw = new PrintWriter(stw);
		ex.printStackTrace(pw);
		log.error(stw.toString());
	}

	@BeforeMethod
	protected void beforeMethod(ITestContext context) {
		this.context = context;

		if (monitor == null) {
			if ("VH".equals(AppConfig.getAppName())) {
				monitor = new DavitaVHMonitor(AppConfig.getPlatform(), AppConfig.getAppEnvirnonment());
			} else if ("HOME".equals(AppConfig.getAppName())) {
				monitor = new DavitaHomeMonitor(AppConfig.getPlatform(), AppConfig.getAppEnvirnonment());
			} else if ("INDIA".equals(AppConfig.getAppName())) {
				monitor = new DavitaIndiaMonitor(AppConfig.getPlatform(), AppConfig.getAppEnvirnonment());
			}
		}
	}

	@SuppressWarnings({ "unchecked" })
	@AfterMethod
	protected void afterTest(Method m, ITestResult result) throws IOException {
		WebDriver webDriver = ((WebDriver) context.getAttribute("web" + m.getName()));
		AppiumDriver<MobileElement> mobileDriver = null;

		if ("Android".equals(AppConfig.getPlatform())) {
			mobileDriver = (AndroidDriver<MobileElement>) context.getAttribute("mobile" + m.getName());
		} else if ("iOS".equals(AppConfig.getPlatform())) {
			mobileDriver = (IOSDriver<MobileElement>) context.getAttribute("mobile" + m.getName());
		}

		if (result.isSuccess()) {
			log.info(Utilities.getCurrentThreadId() + "Test Case PASSED. Proceeding to close the drivers..");

			if (webDriver != null) {
				log.info(Utilities.getCurrentThreadId() + "Closing the web driver for method " + m.getName());
				webDriver.quit();
			}

			if (mobileDriver != null) {
				log.info(Utilities.getCurrentThreadId() + "Closing the mobile driver for method " + m.getName());
				mobileDriver.quit();
			}
			return;
		}

		if ("remote".equals(frameworkProperty.getProperty("executionType"))) {
			webDriver = new Augmenter().augment(webDriver);
		}

		log.warn(Utilities.getCurrentThreadId()
				+ "Test Case FAILED. Proceeding to take screenshot and close the drivers..");

		String dateAndTime = DateAndTime.getFormattedCurrentDateAndTime("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		File screenshot = null;

		if (webDriver != null) {
			screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
			log.info(Utilities.getCurrentThreadId() + "Closing the web driver for method " + m.getName());
			webDriver.quit();
		}

		if (mobileDriver != null) {
			screenshot = ((TakesScreenshot) mobileDriver).getScreenshotAs(OutputType.FILE);
			log.info(Utilities.getCurrentThreadId() + "Closing the mobile driver for method " + m.getName());
			mobileDriver.quit();
		}

		if (screenshot != null) {
			String screenShotName = Utilities.getCurrentThreadId().replace(":", "").replace("\t", "") + dateAndTime
					+ ".png";
			log.info(Utilities.getCurrentThreadId() + "LookOut for screenshot with name " + screenShotName
					+ " under the screenshots folder");
			FileUtils.copyFile(screenshot, new File("./screenshots/" + screenShotName));
		}
		log.info(Utilities.getCurrentThreadId()
				+ "Nothing to log, since the driver was never initialized. Ending the test case");
	}

	@SuppressWarnings("unchecked")
	protected WebDriver getMobileDriver(String mobileKey) {

		AppiumDriver<MobileElement> mobileDriver = null;

		ExecutionAgent agent = new MobileAgent(frameworkProperty.getProperty("network"),
				frameworkProperty.getProperty("manufacturer"), frameworkProperty.getProperty("deviceName"),
				frameworkProperty.getProperty("deviceType"), frameworkProperty.getProperty("deviceId"),
				frameworkProperty.getProperty("platformVersion"), frameworkProperty.getProperty("platformName"),
				frameworkProperty.getProperty("username"), frameworkProperty.getProperty("password"),
				frameworkProperty.getProperty("appPackage"), frameworkProperty.getProperty("appPath"));

		if ("Android".equals(AppConfig.getPlatform())) {
			log.info(Utilities.getCurrentThreadId() + "Getting the Android mobile driver");
			mobileDriver = (AndroidDriver<MobileElement>) new AppiumAndroidDriver().getDriver(agent, monitor);
			context.setAttribute("mobile" + mobileKey, mobileDriver);
			return (AndroidDriver<MobileElement>) context.getAttribute("mobile" + mobileKey);
		} else if ("iOS".equals(AppConfig.getPlatform())) {
			log.info(Utilities.getCurrentThreadId() + "Getting the iOS mobile driver");
			mobileDriver = (IOSDriver<MobileElement>) new AppiumiOSDriver().getDriver(agent, monitor);
			context.setAttribute("mobile" + mobileKey, mobileDriver);
			return (IOSDriver<MobileElement>) context.getAttribute("mobile" + mobileKey);
		} else {
			log.warn(Utilities.getCurrentThreadId() + "Platform name not set. Set it in the framework.properties file");
			return null;
		}
	}

	protected WebDriver getWebDriver(String webKey) {
		ExecutionAgent agent = new BrowserAgent(frameworkProperty.getProperty("browserName"),
				frameworkProperty.getProperty("browserVersion"), Platform.WINDOWS);

		WebDriver webDriver = null;

		if ("remote".equals(frameworkProperty.getProperty("executionType"))) {
			webDriver = new RemoteDriver().getDriver(agent);
		} else {
			webDriver = new LocalDriver().getDriver(agent);
		}

		context.setAttribute("web" + webKey, webDriver);
		return ((WebDriver) context.getAttribute("web" + webKey));
	}
}