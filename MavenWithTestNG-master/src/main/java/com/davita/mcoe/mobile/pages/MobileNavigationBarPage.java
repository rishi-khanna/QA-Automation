package com.davita.mcoe.mobile.pages;

import static com.davita.mcoe.mobile.locators.MobileNavigationBarPageLocators.*;

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
public class MobileNavigationBarPage extends MobilePageBase {

	public MobileNavigationBarPage(WebDriver driver)
			throws WaitException {
		super(driver);
		//Assert.assertTrue(mobileActions.getVisibiltyOfElementLocatedBy(BYNAVIGATIONUP));
	}

	public MobileHomePage tapNavigationUp() throws TimeoutException,
			WaitException {
		mobileActions.tap(VISIBILITY, BYNAVIGATIONUP);
		return new MobileHomePage(driver);
	}
}
