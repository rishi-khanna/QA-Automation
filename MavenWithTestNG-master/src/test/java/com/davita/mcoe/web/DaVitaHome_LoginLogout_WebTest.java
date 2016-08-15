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
public class DaVitaHome_LoginLogout_WebTest extends TestBase {

	@Test()
	public void validateDavitaHomeLoginLogout(Method m) throws Exception {
		// Variables
		WebPageBase pageBase;
		WebLoginPage webLoginPage;
		WebPatientsListPage webPatientsListPage;

		// Navigate to the VH URL
		pageBase = new WebPageBase(getWebDriver(m.getName()));
		webLoginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("DaVitaHomeURL"));

		// Assertions on the Login Page
		Assert.assertTrue(webLoginPage.viewUserNameTextField());
		Assert.assertTrue(webLoginPage.viewPasswordTextField());
		Assert.assertEquals(webLoginPage.getPageTitle(),
				"DaVita Home");

		// Enter UserName and Password
		webLoginPage.enterUserName(applicationProperty
				.getProperty("DaVitaHomeAndVillageUsername"));
		webLoginPage.enterPassword(applicationProperty
				.getProperty("DaVitaHomeAndVillagePassword"));

		// Login into application and perform assertions
		webPatientsListPage = webLoginPage.clickLogin();

		// Logout of the application and perform assertions
		webPatientsListPage.clickSignOut();
		Assert.assertTrue(webLoginPage.viewUserNameTextField());
		Assert.assertTrue(webLoginPage.viewPasswordTextField());
		Assert.assertEquals(webLoginPage.getPageTitle(),
				"DaVita Home");
	}
}