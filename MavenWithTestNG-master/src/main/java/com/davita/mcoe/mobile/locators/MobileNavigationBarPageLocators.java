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
public final class MobileNavigationBarPageLocators {
	
	public static final By BYNAVIGATIONUP = MobileBy
			.AndroidUIAutomator("description(\"Navigate up\")");
	public static final By BYHEADER = By.id("com.pe.davita.home.debug:id/actionbar_TvHeader");
}
