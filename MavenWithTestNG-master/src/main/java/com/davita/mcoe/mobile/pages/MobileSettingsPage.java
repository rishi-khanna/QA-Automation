package com.davita.mcoe.mobile.pages;

import static com.davita.mcoe.mobile.locators.MobileSettingsPageLocators.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.davita.mcoe.exceptions.WaitException;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class MobileSettingsPage extends MobileNavigationBarPage {

	public MobileSettingsPage(WebDriver driver) throws WaitException {
		super(driver);
		// Assert.assertTrue(mobileActions
		// .getVisibiltyOfElementLocatedBy(BYNAVIGATIONUP));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public MobileSettingsPage tapLogout() throws TimeoutException, WaitException {
		mobileActions.tap(VISIBILITY, BYLOGOUT);
		return this;
	}

	public MobileLoginPage tapSignOutOK() throws TimeoutException, WaitException {
		mobileActions.tap(VISIBILITY, BYSIGNOUTOK);
		return new MobileLoginPage(driver);
	}
}
