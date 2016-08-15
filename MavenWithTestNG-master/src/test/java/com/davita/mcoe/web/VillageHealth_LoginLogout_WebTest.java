package com.davita.mcoe.web;

import java.lang.reflect.Method;

import org.testng.Assert;
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
public class VillageHealth_LoginLogout_WebTest extends TestBase {

	@Test()
	public void validateVillageHealthLoginLogout(Method m) throws Exception {
		// Variables
		WebPageBase pageBase;
		WebLoginPage villageHealthWebLoginPage;
		WebPatientsListPage villageHealthPatientsListPage;

		// Navigate to the VH URL
		pageBase = new WebPageBase(getWebDriver(m.getName()));
		villageHealthWebLoginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("VillageHealthURL"));

		// Assertions on the Login Page
		Assert.assertTrue(villageHealthWebLoginPage.viewUserNameTextField());
		Assert.assertTrue(villageHealthWebLoginPage.viewPasswordTextField());
		Assert.assertEquals(villageHealthWebLoginPage.getVillageHealthTitle(),
				"Village Health");

		// Enter UserName and Password
		villageHealthWebLoginPage.enterUserName(applicationProperty
				.getProperty("DaVitaHomeAndVillageUsername"));
		villageHealthWebLoginPage.enterPassword(applicationProperty
				.getProperty("DaVitaHomeAndVillagePassword"));

		// Login into application and perform assertions
		villageHealthPatientsListPage = villageHealthWebLoginPage.clickLogin();

		// Logout of the application and perform assertions
		villageHealthPatientsListPage.clickSignOut();
		Assert.assertTrue(villageHealthWebLoginPage.viewUserNameTextField());
		Assert.assertTrue(villageHealthWebLoginPage.viewPasswordTextField());
		Assert.assertEquals(villageHealthWebLoginPage.getVillageHealthTitle(),
				"Village Health");
	}
}