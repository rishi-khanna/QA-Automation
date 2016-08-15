package com.davita.mcoe.mobile.pages;

import static com.davita.mcoe.mobile.locators.MobileTermsAndConditionsLocators.*;

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
public class MobileTermsAndConditionsPage extends MobilePageBase {

	public MobileTermsAndConditionsPage(WebDriver driver) throws WaitException {
		super(driver);
		// Assert.assertTrue(mobileActions
		// .getVisibiltyOfElementLocatedBy(BYNAVIGATIONUP));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public MobileHomePage acceptTermsAndConditions() throws TimeoutException, WaitException {
		mobileActions.tap(VISIBILITY, BYAGREE);
		return new MobileHomePage(driver);
	}

	public MobileHomePage rejectTermsAndConditions() throws TimeoutException, WaitException {
		mobileActions.tap(VISIBILITY, BYDISAGREE);
		return new MobileHomePage(driver);
	}
}
