package com.davita.mcoe.web.locators;

import org.openqa.selenium.By;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class WebLoginPageLocators {
	public static final By USERNAME = By
			.xpath("//input[@ng-model='nurse.username']");
	public static final By PASSWORD = By
			.xpath("//input[@ng-model='nurse.password']");
	public static final By VHAPPLOGO = By.className("app-logo-login");
	public static final By LOGIN = By.linkText("Login");
}
