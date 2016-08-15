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
public  class MobileHomePageLocators {	
	
	private static String env = "debug";

	public static final By BYDAVITALOGO = By.id("com.pe.davita.home." + env
			+ ":id/davitaLogo");
	public static final By BYWELCOMEMSG = By
			.id("com.villagehealth.pe.debug:id/welcomeMessage");
	public static final By BYANDROIDREMINDERSTEXT = MobileBy.AndroidUIAutomator("description(\"Reminders\")");
	public static final By BYIOSREMINDERSTEXT = MobileBy.id("Reminders");
	public static final By BYKIDNEYCARERESOURCES = MobileBy.AndroidUIAutomator("description(\"Kidney Care Resources\")");
	public static final By BYCONTACTANURSE = MobileBy
			.AndroidUIAutomator("description(\"Contact Clinical Team\")");
	public static final By BYSETTINGS = By.id("com.pe.davita.home." + env
			+ ":id/settingsButton");
}