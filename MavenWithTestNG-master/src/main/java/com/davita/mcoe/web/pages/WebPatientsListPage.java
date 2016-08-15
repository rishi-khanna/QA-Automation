package com.davita.mcoe.web.pages;

import static com.davita.mcoe.web.locators.WebPatientListPageLocators.*;

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
public class WebPatientsListPage extends WebGlobalMenuPage {

	public WebPatientsListPage(WebDriver driver) throws WaitException {
		super(driver);
		//Assert.assertTrue(webActions.getVisibiltyOfElementLocatedBy(VILLAGEHEALTH));
	}

	public boolean validateWaitinRoomLoad() throws WaitException {
		return webActions.getVisibiltyOfElementLocatedBy(VISIBILITY,ADDRESSBOOK);
	}

	public boolean validateAccountsLoad() throws WaitException {
		return webActions.getVisibiltyOfElementLocatedBy(VISIBILITY,ADDNEW);
	}

	public boolean validateResourcesLoad() throws WaitException {
		return webActions.getVisibiltyOfElementLocatedBy(VISIBILITY,REFRESHRESOURCES);
	}

	public WebPatientsPage selectAPatient(String patientName) throws TimeoutException,
			WaitException {
		webActions.selectOption(VISIBILITY, BYPATIENTLIST, patientName);
		return new WebPatientsPage(driver);
	}
}
