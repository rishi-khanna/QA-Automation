package com.davita.mcoe.mobile.pages;

import static com.davita.mcoe.mobile.locators.MobileHomePageLocators.*;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.davita.mcoe.exceptions.WaitException;
import com.davita.mcoe.page.base.MobilePageBase;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class MobileHomePage extends MobilePageBase {

	public MobileHomePage(WebDriver driver) throws WaitException {
		super(driver);
		//Assert.assertTrue(mobileActions.getVisibiltyOfElementLocatedBy(BYDAVITALOGO));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public MobileSettingsPage tapSettings() throws TimeoutException, WaitException {
		mobileActions.tap(VISIBILITY, BYSETTINGS);
		return new MobileSettingsPage(driver);
	}

	public MobileRemindersPage tapReminders() throws TimeoutException, WaitException {
		By BYREMINDERS = frameworkProperty.getProperty("platformName").equals("iOS") ? BYIOSREMINDERSTEXT: BYANDROIDREMINDERSTEXT;
		mobileActions.click(CLICKABILITY, BYREMINDERS);
		return new MobileRemindersPage(driver);
	}
	
	public MobileContactNursePage tapContactANurse()
			throws TimeoutException, WaitException {
		mobileActions.tap(VISIBILITY, BYCONTACTANURSE);
		return new MobileContactNursePage(driver);
	}
	
	public MobileResourcesPage tapKidneyCareResources()
			throws TimeoutException, WaitException {
		mobileActions.tap(VISIBILITY, BYKIDNEYCARERESOURCES);
		return new MobileResourcesPage(driver);
	}
}