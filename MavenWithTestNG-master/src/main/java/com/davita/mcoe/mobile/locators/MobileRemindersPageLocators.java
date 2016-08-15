package com.davita.mcoe.mobile.locators;


import org.openqa.selenium.By;

import com.davita.mcoe.extendable.locator.AppConfig;

import io.appium.java_client.MobileBy;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public final class MobileRemindersPageLocators extends AppConfig{

	public static final By BYREMINDERSTITLE = MobileBy.id(AppConfig.getAppPackage()+"reminders_title_text");
	public static final By BYREMINDERSDESC = MobileBy.id(AppConfig.getAppPackage()+"reminders_item_description_text");
	public static final By BYNOREMINDERSFIRSTTEXT = MobileBy.id(AppConfig.getAppPackage()+"reminders_noreminders_text");
	public static final By BYNOREMINDERSSECONDTEXT = MobileBy.id(AppConfig.getAppPackage()+"reminders_noreminders_secondtext");
	public static final By BYACKNOWLEDGEREMINDER = MobileBy.id(AppConfig.getAppPackage()+"reminders_acknowledge_button");
	public static final By BYIOSREMINDERSCOMPLETEDTAB = MobileBy.id("Completed");
	public static final By BYIOSREMINDERSTODOTAB = MobileBy.id("To Do");
}
