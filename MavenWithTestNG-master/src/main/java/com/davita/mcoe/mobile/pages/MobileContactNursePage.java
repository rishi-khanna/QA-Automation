package com.davita.mcoe.mobile.pages;

import static com.davita.mcoe.mobile.locators.MobileContactANursePageLocators.*;
import static com.davita.mcoe.mobile.locators.MobileNavigationBarPageLocators.*;
import io.appium.java_client.MobileBy;
import ru.yandex.qatools.allure.annotations.Step;

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
public class MobileContactNursePage extends MobileNavigationBarPage {

	public MobileContactNursePage(WebDriver driver)
			throws WaitException {
		super(driver);
		Assert.assertTrue(mobileActions
				.getVisibiltyOfElementLocatedBy(VISIBILITY,BYNAVIGATIONUP));
	}

	@Step
	public MobileContactNursePage tapLiveChat()
			throws TimeoutException, WaitException {
		mobileActions.tap(VISIBILITY, BYLIVECHAT);
		return new MobileContactNursePage(driver);
	}

	public boolean verifyEnteredMessageIsDisplayed(String message)
			throws WaitException {
		return mobileActions.getVisibiltyOfElementLocatedBy(VISIBILITY,MobileBy
				.AndroidUIAutomator("text(\"" + message + "\")"));
	}

	public void typeChatMessage(String message) throws TimeoutException,
			WaitException {
		mobileActions.enterTextWithCheck(VISIBILITY, BYCHATMESSAGETEXTBOX, message);
	}

	public void tapSend() throws TimeoutException, WaitException {
		mobileActions.tap(VISIBILITY, BYSENDMESSAGEBUTTON);
	}
}
