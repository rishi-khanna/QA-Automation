package com.davita.mcoe.web;

import org.testng.annotations.Test;

import com.davita.mcoe.page.base.WebPageBase;
import com.davita.mcoe.test.base.TestBase;
import com.davita.mcoe.web.pages.WebLoginPage;
import com.davita.mcoe.web.pages.WebPatientsListPage;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class DaVitaIndia_ContactANurse_WebTest extends TestBase {

	private WebPageBase pageBase;
	private WebLoginPage loginPage;
	private WebPatientsListPage davitaIndiaWebPatientsListPage;

	@Test
	public void validateContactANurse() throws Exception {
		loginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("applicationURL"));
		loginPage.enterUserName(applicationProperty.getProperty("username"));
		loginPage.enterPassword(applicationProperty.getProperty("password"));
		davitaIndiaWebPatientsListPage = loginPage.clickLogin();
		davitaIndiaWebPatientsListPage.clickWaitingRoom();
		// Add assertions
		davitaIndiaWebPatientsListPage.clickSignOut();
		// Add assertion
	}
}