package com.davita.mcoe.web;

import org.testng.ITestContext;

import org.testng.annotations.Test;

import com.davita.mcoe.page.base.WebPageBase;
import com.davita.mcoe.test.base.TestBase;
import com.davita.mcoe.web.pages.WebLoginPage;
import com.davita.mcoe.web.pages.WebPatientsListPage;
import com.davita.mcoe.web.pages.WebWaitingRoomPage;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class VillageHealth_ContactANurse_WebTest extends TestBase {

	@Test
	public void validateVHWebLoginLogout(ITestContext context) throws Exception {
		WebPageBase pageBase;
		WebLoginPage villageHealthWebLoginPage;
		WebWaitingRoomPage villageHealthWebWaitingRoom;
		WebPatientsListPage villageHealthPatientsListPage;

		pageBase = new WebPageBase(getWebDriver(null));
		// pageBase = new WebPageBase(getWebDriverInstance(context));
		villageHealthWebLoginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("applicationURL"));
		villageHealthWebLoginPage.enterUserName(applicationProperty
				.getProperty("username"));
		villageHealthWebLoginPage
				.enterPassword(util.decodeBase64String(applicationProperty
						.getProperty("password")));
		villageHealthPatientsListPage = villageHealthWebLoginPage.clickLogin();
		villageHealthWebWaitingRoom = villageHealthPatientsListPage
				.clickWaitingRoom();
	}
}