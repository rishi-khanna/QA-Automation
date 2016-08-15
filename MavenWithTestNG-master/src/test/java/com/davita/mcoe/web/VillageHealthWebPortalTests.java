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
public class VillageHealthWebPortalTests extends TestBase {

	private WebPageBase pageBase;
	private WebLoginPage villageHealthWebLoginPage;
	private WebPatientsListPage villageHealthPatientsListPage;

	@Test
	public void validateVHWebLoginLogout() throws Exception {
		villageHealthWebLoginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("applicationURL"));
		villageHealthWebLoginPage.enterUserName(applicationProperty
				.getProperty("username"));
		villageHealthWebLoginPage
				.enterPassword(util.decodeBase64String(applicationProperty
						.getProperty("password")));
		villageHealthPatientsListPage = villageHealthWebLoginPage.clickLogin();
		// Add assertions
		villageHealthPatientsListPage.clickSignOut();
		// Add assertion
	}

	@Test
	public void validateVHWebWaitingRoom() throws Exception {
		villageHealthWebLoginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("applicationURL"));
		villageHealthWebLoginPage.enterUserName("rkhanna");
		villageHealthWebLoginPage
				.enterPassword(util.decodeBase64String(applicationProperty
						.getProperty("password")));
		villageHealthPatientsListPage = villageHealthWebLoginPage.clickLogin();
		villageHealthPatientsListPage.clickWaitingRoom();
		// Add assertions
		villageHealthPatientsListPage.clickSignOut();
	}

	@Test
	public void validateVHWebResources() throws Exception {
		villageHealthWebLoginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("applicationURL"));
		villageHealthWebLoginPage.enterUserName("rkhanna");
		villageHealthWebLoginPage
				.enterPassword(util.decodeBase64String(applicationProperty
						.getProperty("password")));
		villageHealthPatientsListPage = villageHealthWebLoginPage.clickLogin();
		villageHealthPatientsListPage.clickResources();
		// Add assertions
		villageHealthPatientsListPage.clickSignOut();
	}

	@Test
	public void validateVHWebAccounts() throws Exception {
		villageHealthWebLoginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("applicationURL"));
		villageHealthWebLoginPage.enterUserName("rkhanna");
		villageHealthWebLoginPage
				.enterPassword(util.decodeBase64String(applicationProperty
						.getProperty("password")));
		villageHealthPatientsListPage = villageHealthWebLoginPage.clickLogin();
		villageHealthPatientsListPage.clickAccounts();
		// Add assertions
		villageHealthPatientsListPage.clickSignOut();
	}
}