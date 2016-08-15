package com.davita.mcoe.mobile.locators;

import io.appium.java_client.MobileBy;

import org.openqa.selenium.By;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public final class MobileSettingsPageLocators {


	public static final By BYLOGOUT = MobileBy
			.AndroidUIAutomator("new UiSelector().text(\"LOGOUT\")");
	public static final By BYSIGNOUTOK = By.id("android:id/button1");
}