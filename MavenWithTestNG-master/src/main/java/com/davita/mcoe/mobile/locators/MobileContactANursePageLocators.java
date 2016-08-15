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
public final class MobileContactANursePageLocators {

	private static String env = "debug";

	public static final By BYLIVECHAT = MobileBy.AndroidUIAutomator("description(\"Live Chat\")");
	public static final By BYCHATMESSAGETEXTBOX = By.id("com.villagehealth.pe." + env
			+ ":id/chatEditText");
	public static final By BYSENDMESSAGEBUTTON = MobileBy.AndroidUIAutomator("text(\"Send\")");
}
