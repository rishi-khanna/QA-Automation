package com.davita.mcoe.web.pages;

import static com.davita.mcoe.web.locators.WebHomePageLocators.*;

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
public class WebAccountsPage extends WebPageBase {

	public WebAccountsPage(WebDriver driver) throws WaitException {
		super(driver);
		Assert.assertTrue(webActions.getVisibiltyOfElementLocatedBy(VISIBILITY,VILLAGEHEALTH));
	}

	public WebWaitingRoomPage clickWaitingRoom() throws TimeoutException,
			WaitException, InterruptedException {
		webActions.timedClick(VISIBILITY, WAITINGROOM, 2000);
		return new WebWaitingRoomPage(driver);
	}

	public WebAccountsPage clickAccounts() throws TimeoutException, WaitException,
			InterruptedException {
		webActions.timedClick(VISIBILITY, ACCOUNTS, 2000);
		return this;
	}

	public WebAccountsPage clickResources() throws TimeoutException, WaitException,
			InterruptedException {
		webActions.timedClick(VISIBILITY, RESOURCES, 2000);
		return this;
	}

	public WebLoginPage clickSignOut() throws WaitException, TimeoutException,
			InterruptedException {
		webActions.timedClick(VISIBILITY, SIGNOUT, 2000);
		return new WebLoginPage(driver);
	}

	public WebPatientsPage selectPatient(String patientName) throws TimeoutException,
			WaitException {
		webActions.selectOption(VISIBILITY, BYPATIENTLIST, patientName);
		return new WebPatientsPage(driver);
	}
}
