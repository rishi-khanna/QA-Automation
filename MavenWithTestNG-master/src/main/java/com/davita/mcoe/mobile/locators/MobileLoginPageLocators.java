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
public final class MobileLoginPageLocators extends AppConfig{

	public static final By BYUSERNAME = MobileBy.id(AppConfig.getAppPackage()+"login_username_textfield");
	public static final By BYPASSWORD = MobileBy.id(AppConfig.getAppPackage()+"login_password_textfield");
	public static final By BYSIGNIN = MobileBy.id(AppConfig.getAppPackage()+"login_login_button");
	public static final By BYIOSDONE = MobileBy.id("Done");
	public static final By BYIOSENVIRONMENTS = By.xpath("//UIACollectionCell/UIAButton");
	public static final By BYANDROIDENVIRONMENTS = By.id("android:id/text1");
	public static final By BYENVIRONMENTS = MobileBy.id(AppConfig.getAppPackage()+"login_environmentpicker_button");
}