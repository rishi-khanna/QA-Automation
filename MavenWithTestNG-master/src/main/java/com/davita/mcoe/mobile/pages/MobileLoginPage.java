package com.davita.mcoe.mobile.pages;

import static com.davita.mcoe.mobile.locators.MobileLoginPageLocators.*;

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
public class MobileLoginPage extends MobilePageBase {

	public MobileLoginPage(WebDriver driver) throws WaitException {
		super(driver);
		// Assert.assertTrue(mobileActions.getVisibiltyOfElementLocatedBy(BYUSERNAME));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public MobileLoginPage enterUserName(String username) throws TimeoutException, WaitException {
		mobileActions.enterText(VISIBILITY, BYUSERNAME, username);
		return this;
	}

	public MobileLoginPage enterPassword(String password) throws TimeoutException, WaitException {
		mobileActions.enterText(VISIBILITY, BYPASSWORD, password);
		return this;
	}

	public MobileTermsAndConditionsPage tapLogin() throws TimeoutException, WaitException {
		mobileActions.tap(VISIBILITY, BYSIGNIN);
		return new MobileTermsAndConditionsPage(driver);
	}

	public MobileLoginPage hideKeyboard() throws TimeoutException, WaitException {
		mobileActions.hideKeyboard();
		return this;
	}

	public MobileLoginPage acceptLoginPageNotification() throws WaitException {
		mobileActions.switchToAcceptAlert(NOTREQUIRED);
		return this;
	}

	public MobileLoginPage clickDoneOnKeyboardToolBar() throws TimeoutException, WaitException {
		mobileActions.tap(VISIBILITY, BYIOSDONE);
		return this;
	}

	public MobileLoginPage changeEnvironmentTo(String env) throws TimeoutException, WaitException {
		if ("iOS".equals(frameworkProperty.getProperty("platformName")))
			mobileActions.selectOption(VISIBILITY, BYIOSENVIRONMENTS, env);
		else
			mobileActions.selectOption(VISIBILITY, BYANDROIDENVIRONMENTS, env);
		return this;
	}

	public MobileLoginPage tapDEVButton() throws TimeoutException, WaitException {
		mobileActions.tap(VISIBILITY, BYENVIRONMENTS);
		return this;
	}

	public MobileLoginPage showKeyboard() throws TimeoutException, WaitException {
		mobileActions.showKeyboard();
		return this;
	}

	public String checkEnvironmentSelected() throws WaitException {
		return mobileActions.getText(VISIBILITY, BYENVIRONMENTS);
	}

	public MobileHomePage hackToGetAroundTermsAndConditionsInAndroid() throws WaitException {
		return new MobileHomePage(driver);
	}

	public MobileTermsAndConditionsPage tapLogin(String platform) throws TimeoutException, WaitException {
		mobileActions.tap(VISIBILITY, BYSIGNIN);
		return new MobileTermsAndConditionsPage(driver);
	}
}
