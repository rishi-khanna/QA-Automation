package com.davita.mcoe.mobile.locators;

import org.openqa.selenium.By;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public final class MobileResourcesPageLocators {

	private static String env = "debug"; // debug,qa,dev

	public static final By BYRECOMMENDEDRESOURCESLIST = By.id("com.villagehealth.pe." + env
			+ ":id/resources_tvTitle");
	public static final By BYPDFVIEW = By.id("com.google.android.apps.docs:id/pdf_view");
}
