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
public class DaVitaIndia_LoginLogout_WebTest extends TestBase {

	@Test()
	public void validateDavitaIndiaLoginLogout(Method m) throws Exception {
		// Variables
		WebPageBase pageBase;
		WebLoginPage davitaIndiaWebLoginPage;
		WebPatientsListPage davitaIndiaPatientsListPage;

		// Navigate to the VH URL
		pageBase = new WebPageBase(getWebDriver(m.getName()));
		davitaIndiaWebLoginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("DaVitaIndiaURL"));

		// Assertions on the Login Page
		Assert.assertTrue(davitaIndiaWebLoginPage.viewUserNameTextField());
		Assert.assertTrue(davitaIndiaWebLoginPage.viewPasswordTextField());
		Assert.assertEquals(davitaIndiaWebLoginPage.getPageTitle(),
				"DaVita India");

		// Enter UserName and Password
		davitaIndiaWebLoginPage.enterUserName(applicationProperty
				.getProperty("DaVitaIndiaUsername"));
		davitaIndiaWebLoginPage.enterPassword(applicationProperty
				.getProperty("DaVitaIndiaPassword"));

		// Login into application and perform assertions
		davitaIndiaPatientsListPage = davitaIndiaWebLoginPage.clickLogin();

		// Logout of the application and perform assertions
		davitaIndiaPatientsListPage.clickSignOut();
		Assert.assertTrue(davitaIndiaWebLoginPage.viewUserNameTextField());
		Assert.assertTrue(davitaIndiaWebLoginPage.viewPasswordTextField());
		Assert.assertEquals(davitaIndiaWebLoginPage.getPageTitle(),
				"DaVita India");
	}
}