package com.davita.mcoe.mobile.pages;

import static com.davita.mcoe.mobile.locators.MobileNavigationBarPageLocators.*;
import static com.davita.mcoe.mobile.locators.MobileResourcesPageLocators.*;

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
public class MobileResourcesPage extends MobileNavigationBarPage {

	public MobileResourcesPage(WebDriver driver) throws WaitException {
		super(driver);
		Assert.assertTrue(mobileActions.getVisibiltyOfElementLocatedBy(VISIBILITY,BYNAVIGATIONUP));
	}

	public void tapAssignedResourceWithName(String resourceName) throws TimeoutException,
			WaitException {
		mobileActions.selectOption(VISIBILITY, BYRECOMMENDEDRESOURCESLIST, resourceName);
	}

	public boolean verifyPDFContainer() throws WaitException {
		return mobileActions.getVisibiltyOfElementLocatedBy(VISIBILITY,BYPDFVIEW);
	}
}