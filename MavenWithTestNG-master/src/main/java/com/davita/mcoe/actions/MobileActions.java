package com.davita.mcoe.actions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.HideKeyboardStrategy;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.davita.mcoe.exceptions.WaitException;
import com.davita.mcoe.utilities.Utilities;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class MobileActions extends WebActions {

	private AppiumDriver<MobileElement> mobileDriver;

	@SuppressWarnings("unchecked")
	public MobileActions(WebDriver driver) {
		super(driver);
		this.mobileDriver = (AppiumDriver<MobileElement>) super.driver;
	}

	public void tap(String syncKey, By locator) throws TimeoutException, WaitException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Performing tap action on locator:" + locator);
		TouchAction action = new TouchAction(mobileDriver);
		action.tap(wait.syncElementUsing(syncKey, mobileDriver, locator)).perform();
		LOGGER.info(Utilities.getCurrentThreadId() + "Tapped on element with locator:" + locator);
	}

	public void tapAtElementPoint(String syncKey, By locator) throws TimeoutException, WaitException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Performing tap on element at point");
		int x = wait.syncElementUsing(syncKey, mobileDriver, locator).getLocation().getX();
		int y = wait.syncElementUsing(syncKey, mobileDriver, locator).getLocation().getY();
		LOGGER.info(Utilities.getCurrentThreadId() + "Performing tap on element at point X=" + x + "Y=" + y);
		TouchAction action = new TouchAction(mobileDriver);
		action.tap(x, y).perform();
		LOGGER.info(Utilities.getCurrentThreadId() + "Tapped on element at point:" + locator);
	}

	public void swipeOnScreen(int xstart, int ystart, int xend, int yend, int duration) {
		LOGGER.info(Utilities.getCurrentThreadId() + "Performing swipe on screen from Start X=" + xstart + " Start Y="
				+ ystart + " to End X=" + xend + " End Y=" + yend);
		mobileDriver.swipe(xstart, ystart, xend, yend, duration);
		LOGGER.info(Utilities.getCurrentThreadId() + "Swiped on screen with coordinates Start X=" + xstart + " Start Y="
				+ ystart + " End X=" + xend + " End Y=" + yend);
	}

	public void setOrientationTo(String orientation) {
		LOGGER.info(Utilities.getCurrentThreadId() + "Changing the Orientation of Device");
		if ("Landscape".equals(orientation)) {
			LOGGER.info(Utilities.getCurrentThreadId() + "Changing the Orientation to Landscape");
			mobileDriver.rotate(ScreenOrientation.LANDSCAPE);
			LOGGER.info(Utilities.getCurrentThreadId() + "Device in LANDSCAPE Mode");
		} else {
			LOGGER.info(Utilities.getCurrentThreadId() + "Changing the Orientation to Landscape");
			mobileDriver.rotate(ScreenOrientation.PORTRAIT);
			LOGGER.info(Utilities.getCurrentThreadId() + "Device in PORTRAIT Mode");
		}
	}

	public void closeApp() {
		LOGGER.info(Utilities.getCurrentThreadId() + "Closing the application");
		mobileDriver.closeApp();
		LOGGER.info(Utilities.getCurrentThreadId() + "Application Closed...");
	}

	public void hideKeyboard() {
		LOGGER.info(Utilities.getCurrentThreadId() + "Hiding the Keyboard");
		try {
			mobileDriver.hideKeyboard();
		} catch (Exception ex) {
			LOGGER.info(Utilities.getCurrentThreadId() + "Exception in closing the keyboard. Maybe it was never Open");
		}
		LOGGER.info(Utilities.getCurrentThreadId() + "Keyboard Hidden...");
	}

	public void showKeyboard() {
		LOGGER.info(Utilities.getCurrentThreadId() + "Displaying the Keyboard");
		mobileDriver.getKeyboard();
		LOGGER.info(Utilities.getCurrentThreadId() + "Keyboard Displayed...");
	}

	private Dimension getScreenDimensions() {
		Dimension dim = mobileDriver.manage().window().getSize();
		LOGGER.info(Utilities.getCurrentThreadId() + "Screen Dimension = " + dim);
		return mobileDriver.manage().window().getSize();
	}

	public int getVerticalScrollStartPoint(double distanceFromTop) {
		int starty = (int) (getScreenDimensions().height * distanceFromTop);
		return starty;
	}

	public int getVerticalScrollEndPoint(double distanceFromBottom) {
		int endy = (int) (getScreenDimensions().height * distanceFromBottom);
		return endy;
	}

	public int getHorizontalScrollStartPoint(double distanceFromLeft) {
		int startx = (int) (getScreenDimensions().width * distanceFromLeft);
		return startx;
	}

	public void swipeTo(String syncKey, By element) throws TimeoutException, WaitException, WebDriverException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Swiping on element with locator:" + element);
		((MobileElement) wait.syncElementUsing(syncKey, mobileDriver, element)).swipe(SwipeElementDirection.UP, 10);
		LOGGER.info(Utilities.getCurrentThreadId() + "Swiping on element with locator:" + element);
	}

	public Point androidScrollToText(String syncKey, String text) throws WaitException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Scrolling Android Text " + text + " into View");
		By androidScrollElement = MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(" + "new UiSelector().text(\"" + text + "\"));");
		MobileElement element;
		if ("notrequired".equals(syncKey)) {
			element = mobileDriver.findElement(androidScrollElement);
		} else {
			element = (MobileElement) wait.syncElementUsing(syncKey, mobileDriver, androidScrollElement);
		}
		Point location = element.getLocation();
		LOGGER.info(Utilities.getCurrentThreadId() + "Android Text Scrolled into View at Location " + location);
		return location;
	}

	public Point iOSScrollToText(String syncKey, String text) throws WaitException {
		LOGGER.info(Utilities.getCurrentThreadId() + "Scrolling iOS Text " + text + " into View");
		By iOSScrollElement = MobileBy.IosUIAutomation(
				".tableViews()[0]" + ".scrollToElementWithPredicate(\"label CONTAINS '" + text + "'\")");
		MobileElement element;
		if ("notrequired".equals(syncKey)) {
			element = mobileDriver.findElement(iOSScrollElement);
		} else {
			element = (MobileElement) wait.syncElementUsing(syncKey, mobileDriver, iOSScrollElement);
		}
		Point location = element.getLocation();
		LOGGER.info(Utilities.getCurrentThreadId() + "iOS Text Scrolled into View at Location " + location);
		return location;
	}
}