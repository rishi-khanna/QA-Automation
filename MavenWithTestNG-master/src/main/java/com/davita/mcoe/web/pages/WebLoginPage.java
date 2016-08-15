package com.davita.mcoe.web.pages;

import static com.davita.mcoe.web.locators.WebLoginPageLocators.*;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.davita.mcoe.exceptions.WaitException;
import com.davita.mcoe.page.base.WebPageBase;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class WebLoginPage extends WebPageBase {

	public WebLoginPage(WebDriver driver) throws WaitException {
		super(driver);
		// Assert.assertTrue(webActions.getVisibiltyOfElementLocatedBy(VHAPPLOGO));
	}

	public boolean viewUserNameTextField() throws TimeoutException, WaitException {
		return webActions.getVisibiltyOfElementLocatedBy(VISIBILITY, USERNAME);
	}

	public boolean viewPasswordTextField() throws TimeoutException, WaitException {
		return webActions.getVisibiltyOfElementLocatedBy(VISIBILITY, PASSWORD);
	}

	public String getVillageHealthTitle() throws TimeoutException, WaitException {
		return webActions.getTitle();
	}

	public WebLoginPage enterUserName(String userNameVal) throws TimeoutException, WaitException {
		webActions.enterText(VISIBILITY, USERNAME, userNameVal);
		return this;
	}

	public WebLoginPage enterPassword(String password) throws TimeoutException, WaitException {
		webActions.enterText(VISIBILITY, PASSWORD, password);
		return this;
	}

	public WebPatientsListPage clickLogin() throws TimeoutException, WaitException {
		webActions.click(VISIBILITY, LOGIN);
		return new WebPatientsListPage(driver);
	}

	public WebGlobalMenuPage loginIntoApplication(String userName, String password)
			throws TimeoutException, WaitException {
		enterUserName(userName);
		enterPassword(password);
		webActions.click(VISIBILITY, LOGIN);
		return new WebGlobalMenuPage(driver);
	}

	public String getPageTitle() throws TimeoutException, WaitException {
		return webActions.getTitle();
	}
}
