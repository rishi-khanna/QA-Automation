package com.davita.mcoe.extendable.locator;

import java.util.Properties;

import com.davita.mcoe.property.reader.PropertyManager;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class AppConfig {

	private static final Properties APPLICATIONPROPERTY = PropertyManager
			.loadApplicationPropertyFile("application.properties");

	private static final Properties FRAMEWORKPROPERTY = PropertyManager
			.loadFrameworkPropertyFile("framework.properties");

	private static String PACKAGE;
	private static String appName = APPLICATIONPROPERTY.getProperty("app");
	private static String environment = APPLICATIONPROPERTY.getProperty("env");
	private static String platform = FRAMEWORKPROPERTY.getProperty("platformName");

	static {

		if ("iOS".equals(getPlatform())) {
			PACKAGE = "";
		}

		else {

			// Package
			String appPackage;
			if (appName.equals("HOME")) {
				appPackage = "com.pe.davita.home.";
			} else if (appName.equals("INDIA")) {
				appPackage = "com.pe.davita.india.";
			} else {
				appPackage = "com.villagehealth.pe.";
			}

			PACKAGE = appPackage + "debug" + ":id/";
		}
	}
	
	public static String getAppName(){
		return appName;
	}

	public static String getAppEnvirnonment(){
		return environment;
	}
	
	public static String getAppPackage(){
		return PACKAGE;
	}
	
	public static String getPlatform(){
		return platform;
	}
}