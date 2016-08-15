package com.davita.mcoe.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;

import com.davita.mcoe.comparator.Comparator;
import com.davita.mcoe.exceptions.URLNavigationException;
import com.davita.mcoe.exceptions.WaitException;
import com.davita.mcoe.property.reader.PropertyManager;
import com.davita.mcoe.utilities.Logg;
import com.davita.mcoe.utilities.Utilities;
import com.davita.mcoe.waits.WebDriverWaits;

import io.appium.java_client.MobileBy;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class WebActions {

	protected WebDriver driver;
	protected final Comparator compare = new Comparator();
	protected static final Logger LOGGER = Logg.createLogger();
	protected final WebDriverWaits wait = new WebDriverWaits();
	private static Cookie cookie;
	private static final Properties APPLICATIONPROPERTY = PropertyManager
			.loadApplicationPropertyFile("application.properties");

	public WebActions(WebDriver driver) {
		this.driver = driver;
	}

	public void storeDataInCookie(String key, String value) {
		cookie = new Cookie(key, value);
		LOGGER.info(Utilities.getCurrentThreadId() + "Cookie description");
		LOGGER.info(Utilities.getCurrentThreadId() + "Key=" + key + " " + "Value=" + value);
		driver.manage().addCookie(cookie);
		LOGGER.info(Utilities.getCurrentThreadId() + "Successfully added cookie named " + key + " to the HTML page");
	}

	public String retrieveDataFromCookie(String key) {
		LOGGER.info(Utilities.getCurrentThreadId() + "Retrieving the value "
				+ driver.manage().getCookieNamed(key).getValue() + " stored in the cookie");
		return driver.manage().getCookieNamed(key).getValue();
	}

	public void navigateToURL(String webUrl) throws URLNavigationException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Navigating to Application URL on Browser:" + webUrl);
		driver.get(webUrl);
		LOGGER.info(Utilities.getCurrentThreadId() + "Successfully navigated to Application URL on the Browser");
	}

	public void maximizeBrowser() {
		LOGGER.info(Utilities.getCurrentThreadId() + "Maximizing the browser");
		driver.manage().window().maximize();
		LOGGER.info(Utilities.getCurrentThreadId() + "Browser Successfully Maximized");
	}

	public void closeBrowser(ITestContext context) {
		LOGGER.info(Utilities.getCurrentThreadId() + "Closing the browser");
		context.getAttribute(context.getCurrentXmlTest().getName());
		LOGGER.info(Utilities.getCurrentThreadId() + "Sucessfully closed the browser" + "\n");
	}

	public void enterText(String syncKey, By element, String value) throws TimeoutException, WaitException {
		WebElement webElement = null;
		webElement = wait.syncElementUsing(syncKey, driver, element);
		LOGGER.info(Utilities.getCurrentThreadId() + "Clearing the content of the text box");
		webElement.clear();
		LOGGER.info(Utilities.getCurrentThreadId() + "Contents cleared");
		webElement.sendKeys(value);
		LOGGER.info(Utilities.getCurrentThreadId() + "Entered text:" + webElement.getText()
				+ " in text box with locator:" + element);
	}

	public void enterTextWithCheck(String syncKey, By element, String value) throws TimeoutException, WaitException {
		WebElement webElement = null;
		webElement = wait.syncElementUsing(syncKey, driver, element);
		LOGGER.info(Utilities.getCurrentThreadId() + "Clearing the content of the text box");
		webElement.clear();
		LOGGER.info(Utilities.getCurrentThreadId() + "Contents cleared");
		webElement.sendKeys(value);
		while (true) {
			if (webElement.getText().equals(value)) {
				break;
			} else {
				enterTextWithCheck(syncKey, element, value);
			}

		}
		LOGGER.info(Utilities.getCurrentThreadId() + "Entered text:" + webElement.getText()
				+ " in text box with locator:" + element);
	}

	public void enterKey(String syncKey, By element, Keys keyType) throws TimeoutException, WaitException {
		WebElement webElement = null;
		LOGGER.info(
				Utilities.getCurrentThreadId() + "Pressing the Keyboard Key:" + keyType + " for locator " + element);
		webElement = wait.syncElementUsing(syncKey, driver, element);
		webElement.sendKeys(keyType);
		LOGGER.info(Utilities.getCurrentThreadId() + "Key " + keyType + " pressed for locator:" + element);
	}

	public void click(String syncKey, By element) throws TimeoutException, WaitException, WebDriverException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Clicking on element with locator:" + element);
		wait.syncElementUsing(syncKey, driver, element).click();
		LOGGER.info(Utilities.getCurrentThreadId() + "Clicked on element with locator:" + element);
	}

	public void timedClick(String syncKey, By element, int timeout)
			throws TimeoutException, WaitException, InterruptedException, WebDriverException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Clicking after " + timeout + " secs on element with locator:"
				+ element);
		wait.waitForTimePeriod(timeout);
		wait.syncElementUsing(syncKey, driver, element).click();
		LOGGER.info(Utilities.getCurrentThreadId() + "Clicked on element with locator:" + element);
	}

	public void contextClick(String syncKey, By element) throws TimeoutException, WaitException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Right clicking on element with locator:" + element);
		Actions action = new Actions(driver);
		action.contextClick(wait.syncElementUsing(syncKey, driver, element)).perform();
		LOGGER.info(Utilities.getCurrentThreadId() + "Right click performed on element with locator:" + element);
	}

	public void clickByJQuery(String element) {
		LOGGER.info(Utilities.getCurrentThreadId() + "Clicking on element with locator:" + element + " using JQuery");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("$('" + element + "').click()");
		LOGGER.info("Clicked on element with locator:" + element + " using JQuery");
	}

	public void submitForm(String syncKey, By element) throws TimeoutException, WaitException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Submitting the form using locator:" + element);
		wait.syncElementUsing(syncKey, driver, element).submit();
		LOGGER.info(Utilities.getCurrentThreadId() + "Form Submitted for element:" + element);
	}

	public void switchToSecondaryWindow(String windowTitle) throws WaitException, InterruptedException {
		wait.waitForTimePeriod(10000);
		LOGGER.info(Utilities.getCurrentThreadId() + "Secondary window title for switching: " + windowTitle);
		Set<String> windows = driver.getWindowHandles();
		LOGGER.info(Utilities.getCurrentThreadId() + "Windows=" + windows.toString());
		for (String strWindows : windows) {
			if (driver.switchTo().window(strWindows).getTitle().equals(windowTitle)) {
				LOGGER.info(Utilities.getCurrentThreadId() + "Switched to the window with title: "
						+ driver.switchTo().window(strWindows).getTitle());
				driver.switchTo().window(strWindows).manage().window().maximize();
				LOGGER.info(Utilities.getCurrentThreadId() + "Maximized the window with title "
						+ driver.switchTo().window(strWindows).getTitle());
				break;
			}
		}
	}

	public void selectOption(String syncKey, By parentLocator, String value) throws TimeoutException, WaitException {
		List<WebElement> element = wait.syncElementsUsing(syncKey, driver, parentLocator);
		LOGGER.info(Utilities.getCurrentThreadId() + "Size of the elements in the list=" + element.size());
		LOGGER.info(Utilities.getCurrentThreadId() + "Elements=" + element.toString());
		for (int i = 0; i < element.size(); i++) {
			String temp = element.get(i).getText().replace((char) 0x00a0, (char) 0x0020);
			if (compare.compareExactText(value, temp.trim())) {
				LOGGER.info(Utilities.getCurrentThreadId() + " " + "Clicking on option " + value);
				element.get(i).click();
				LOGGER.info(Utilities.getCurrentThreadId() + "Successfully Clicked on option " + temp);
				break;
			}
		}
	}

	public void selectFromDropDown(String syncKey, By locator, String value) throws TimeoutException, WaitException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Selecting " + value + " from drop-down with locator:" + locator);

		WebElement element;

		if ("notrequired".equals(syncKey)) {
			element = driver.findElement(locator);
		} else {
			element = wait.syncElementUsing(syncKey, driver, locator);
		}

		Select select = new Select(element);
		select.selectByVisibleText(value);

		LOGGER.info(Utilities.getCurrentThreadId() + "Selected:" + value + " from drop-down with locator:" + element);
	}

	public String getText(String syncKey, By element) throws TimeoutException, WaitException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Retrieving the TEXT of element with locator:" + element);
		String actual;
		if ("notrequired".equals(syncKey)) {
			actual = driver.findElement(element).getText();
		} else {
			actual = wait.syncElementUsing(syncKey, driver, element).getText();
		}
		LOGGER.info(Utilities.getCurrentThreadId() + "Actual Value:" + actual);
		return actual;
	}

	public String getTitle() {
		LOGGER.info(Utilities.getCurrentThreadId() + "Title of the page:" + driver.getTitle());
		return driver.getTitle();
	}

	public String getAttributeValue(String syncKey, By element, String attribute)
			throws TimeoutException, WaitException {
		LOGGER.info(
				Utilities.getCurrentThreadId() + "Retrieving the attribute " + attribute + " of element " + element);

		String attributeValue;

		if ("notrequired".equals(syncKey)) {
			attributeValue = driver.findElement(element).getAttribute(attribute);
		} else {
			attributeValue = wait.syncElementUsing(syncKey, driver, element).getAttribute(attribute);
		}

		return attributeValue;
	}

	public List<String> getWebElementsTextInList(String syncKey, By locator) throws TimeoutException, WaitException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Coverting the locator into a List of String");

		List<WebElement> weblElementList;

		if ("notrequired".equals(syncKey)) {
			weblElementList = driver.findElements(locator);
		} else {
			weblElementList = wait.syncElementsUsing(syncKey, driver, locator);
		}

		LOGGER.info(Utilities.getCurrentThreadId() + "List of size=" + weblElementList.size() + " elements created");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < weblElementList.size(); i++) {
			list.add(weblElementList.get(i).getText());
		}
		LOGGER.info(Utilities.getCurrentThreadId() + "List Elements=" + list);

		return list;
	}

	public Boolean getVisibiltyOfElementLocatedBy(String syncKey, By locator) throws WaitException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Checking for Visibility of element");

		Boolean isVisible;

		if ("notrequired".equals(syncKey)) {
			isVisible = driver.findElement(locator).isDisplayed();
		} else {
			isVisible = wait.checkForElementVisibility(driver, locator);
		}

		return isVisible;
	}

	public void pressModifierKey(Keys key) {
		LOGGER.info(Utilities.getCurrentThreadId() + "Pressing the Keyboard Key " + key + " usign the Action Class");
		Actions action = new Actions(driver);
		action.keyDown(key);
		LOGGER.info(Utilities.getCurrentThreadId() + "Pressing the Keyboard Key " + key + " usign the Action Class");
	}

	public void pressKeys(Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(key);
	}

	public void actionClick(String syncKey, By element) throws TimeoutException, WaitException {
		Actions action = new Actions(driver);
		action.moveToElement(wait.syncElementUsing(syncKey, driver, element)).click().perform();
	}

	public void switchToFrame(String syncKey, By locator) throws TimeoutException, WaitException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Switching to HTML frame with locator " + locator);
		driver.switchTo().frame(wait.syncElementUsing(syncKey, driver, locator));
		LOGGER.info(Utilities.getCurrentThreadId() + "Switched to frame with locator:" + locator);
	}

	public void switchToParentFrame() throws TimeoutException, WaitException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Switching to Parent HTML Frame");
		driver.switchTo().defaultContent();
		LOGGER.info(Utilities.getCurrentThreadId() + "Switched to the Parent HTML Frame");
	}

	public void switchToAcceptAlert(String syncKey) throws WaitException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Switching to Accept the Alert");
		try {
			if ("notrequired".equals(syncKey)) {
				driver.switchTo().alert().accept();
			} else {
				wait.waitForAlert(driver).accept();
			}
			LOGGER.info(Utilities.getCurrentThreadId() + "Alert Accepted");
		} catch (NoAlertPresentException ex) {
			LOGGER.info(Utilities.getCurrentThreadId() + "Alert Not Found");
		}
	}

	public void switchToDismissAlert() throws WaitException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Switching to Dismiss the Alert");
		wait.waitForAlert(driver).dismiss();
		LOGGER.info(Utilities.getCurrentThreadId() + "Alert Dismissed");
	}

	public void getPageSource() throws WaitException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Switching to Dismiss the Alert");
		String pageSource = driver.getPageSource();
		LOGGER.info(Utilities.getCurrentThreadId() + pageSource);
	}
}
