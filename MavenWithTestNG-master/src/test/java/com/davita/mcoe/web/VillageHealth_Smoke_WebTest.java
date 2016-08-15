package com.davita.mcoe.web;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.davita.mcoe.page.base.WebPageBase;
import com.davita.mcoe.test.base.TestBase;
import com.davita.mcoe.web.pages.WebLoginPage;
import com.davita.mcoe.web.pages.WebPatientsListPage;
import com.davita.mcoe.web.pages.WebPatientsPage;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class VillageHealth_Smoke_WebTest extends TestBase {

	@Test()
	public void smokeTest1(Method m) throws Exception {
		WebPageBase pageBase;
		WebLoginPage villageHealthWebLoginPage;

		pageBase = new WebPageBase(getWebDriver(m.getName()));
		villageHealthWebLoginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("applicationURL"));

		Assert.assertTrue(villageHealthWebLoginPage.viewUserNameTextField());
		Assert.assertTrue(villageHealthWebLoginPage.viewPasswordTextField());
		Assert.assertEquals(villageHealthWebLoginPage.getVillageHealthTitle(), "Village Health");
	}

	@Test()
	public void smokeTest2(Method m) throws Exception {
		WebPageBase pageBase;
		WebLoginPage villageHealthWebLoginPage;

		pageBase = new WebPageBase(getWebDriver(m.getName()));
		villageHealthWebLoginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("applicationURL"));

		Assert.assertTrue(villageHealthWebLoginPage.viewUserNameTextField());
		Assert.assertTrue(villageHealthWebLoginPage.viewPasswordTextField());
		Assert.assertEquals(villageHealthWebLoginPage.getVillageHealthTitle(), "Village Health");
	}

	@Test(dataProvider = "ReadExcel")
	public void smokeTest3(Method m, String firstName, String lastName, String id, String emailid,
			String phoneNumber, String dob, String age) throws Exception {
		WebPageBase pageBase;
		WebLoginPage villageHealthWebLoginPage;
		WebPatientsListPage villageHealthWebPatientsListPage;
		WebPatientsPage villageHealthWebPatientsPage;

		pageBase = new WebPageBase(getWebDriver(m.getName()));
		villageHealthWebLoginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("applicationURL"));
		villageHealthWebLoginPage.enterUserName(applicationProperty.getProperty("username"));
		villageHealthWebLoginPage.enterPassword(util.decodeBase64String(applicationProperty
				.getProperty("password")));
		villageHealthWebPatientsListPage = villageHealthWebLoginPage.clickLogin();

		villageHealthWebPatientsPage = villageHealthWebPatientsListPage.selectAPatient(lastName
				+ ", " + firstName);

		Assert.assertTrue(villageHealthWebPatientsPage.validatePatientName(firstName + " "
				+ lastName));
		Assert.assertTrue(villageHealthWebPatientsPage.comparePatientID("ID: " + id));
		Assert.assertTrue(villageHealthWebPatientsPage.comparePatientEmailId(emailid));
		Assert.assertTrue(villageHealthWebPatientsPage.comparePatientPhoneNumber(phoneNumber));
		Assert.assertTrue(villageHealthWebPatientsPage.comparePatientDOB(dob));
		Assert.assertTrue(villageHealthWebPatientsPage.comparePatientAge(age));
		Assert.assertTrue(villageHealthWebPatientsPage.viewAssignResourceButton());
		Assert.assertTrue(villageHealthWebPatientsPage.viewAssignReminderButtonAtTopRight());
		Assert.assertTrue(villageHealthWebPatientsPage.viewAssignReminderButton());
		Assert.assertTrue(villageHealthWebPatientsPage.viewFilter());
		Assert.assertTrue(villageHealthWebPatientsPage.viewSearchBar());

		villageHealthWebPatientsPage.clickSignOut();
	}

	@Test
	public void smokeTest4(Method m) throws Exception {
		WebPageBase pageBase;
		WebLoginPage villageHealthWebLoginPage;
		WebPatientsListPage villageHealthWebPatientsListPage;
		WebPatientsPage villageHealthWebPatientsPage;

		pageBase = new WebPageBase(getWebDriver(m.getName()));
		villageHealthWebLoginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("applicationURL"));
		villageHealthWebLoginPage.enterUserName(applicationProperty.getProperty("username"));
		villageHealthWebLoginPage.enterPassword(util.decodeBase64String(applicationProperty
				.getProperty("password")));
		villageHealthWebPatientsListPage = villageHealthWebLoginPage.clickLogin();

		villageHealthWebPatientsPage = villageHealthWebPatientsListPage.selectAPatient("chen"
				+ ", " + "paige");

		villageHealthWebPatientsPage.clickAssignReminders();

		Assert.assertTrue(villageHealthWebPatientsPage.viewApptsTab());
		Assert.assertTrue(villageHealthWebPatientsPage.viewMedsTab());
		Assert.assertTrue(villageHealthWebPatientsPage.viewSelfCareTab());
		Assert.assertTrue(villageHealthWebPatientsPage.viewLabsTab());
		Assert.assertTrue(villageHealthWebPatientsPage.viewNutritionTab());

		villageHealthWebPatientsPage.clickMedsTab();// workaround for hidden
													// cancel button

		villageHealthWebPatientsPage.clickCancel();

		villageHealthWebPatientsPage.clickSignOut();
	}

	@Test
	public void smokeTest5(Method m) throws Exception {
		WebPageBase pageBase;
		WebLoginPage villageHealthWebLoginPage;
		WebPatientsListPage villageHealthWebPatientsListPage;
		WebPatientsPage villageHealthWebPatientsPage;

		pageBase = new WebPageBase(getWebDriver(m.getName()));
		villageHealthWebLoginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("applicationURL"));
		villageHealthWebLoginPage.enterUserName(applicationProperty.getProperty("username"));
		villageHealthWebLoginPage.enterPassword(util.decodeBase64String(applicationProperty
				.getProperty("password")));
		villageHealthWebPatientsListPage = villageHealthWebLoginPage.clickLogin();
		villageHealthWebPatientsPage = villageHealthWebPatientsListPage.selectAPatient("chen"
				+ ", " + "paige");
		villageHealthWebPatientsPage.clickAssignReminders();
		villageHealthWebPatientsPage.clickMedsTab();
		villageHealthWebPatientsPage.clickRecurringCheckBox();

		Assert.assertTrue(villageHealthWebPatientsPage.viewRecurringFilter());
		villageHealthWebPatientsPage.clickRecurringFilter();
		Assert.assertTrue(villageHealthWebPatientsPage.viewRecurringFilterOptions(Arrays.asList(
				"Daily", "Weekly", "Monthly")));

		villageHealthWebPatientsPage.clickCancel();

		villageHealthWebPatientsPage.clickSignOut();
	}

	@Test
	public void smokeTest6(Method m) throws Exception {
		WebPageBase pageBase;
		WebLoginPage villageHealthWebLoginPage;
		WebPatientsListPage villageHealthWebPatientsListPage;
		WebPatientsPage villageHealthWebPatientsPage;

		pageBase = new WebPageBase(getWebDriver(m.getName()));
		villageHealthWebLoginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("applicationURL"));
		villageHealthWebLoginPage.enterUserName(applicationProperty.getProperty("username"));
		villageHealthWebLoginPage.enterPassword(util.decodeBase64String(applicationProperty
				.getProperty("password")));
		villageHealthWebPatientsListPage = villageHealthWebLoginPage.clickLogin();
		villageHealthWebPatientsPage = villageHealthWebPatientsListPage.selectAPatient("chen"
				+ ", " + "paige");
		villageHealthWebPatientsPage.clickAssignReminders();
		villageHealthWebPatientsPage.clickRecurringCheckBox();
		villageHealthWebPatientsPage.clickRecurringFilter();
		villageHealthWebPatientsPage.selectRecurringOption("Daily");

		villageHealthWebPatientsPage.selectDailyReminderStartDate("17");
		villageHealthWebPatientsPage.selectDailyReminderEndDate("18");

		villageHealthWebPatientsPage.enterReminderTitle("this is automated reminder");
		villageHealthWebPatientsPage.enterReminderDescription("this is automated reminder");

		villageHealthWebPatientsPage.clickReminderFlag();

		villageHealthWebPatientsPage.clickAssign();

		Assert.assertEquals(villageHealthWebPatientsPage.getReminderSuccessMessage(),
				"Reminder created successfully");

		villageHealthWebPatientsPage.clickOk();

		villageHealthWebPatientsPage.clickSignOut();
	}

	@Test
	public void smokeTest7(Method m) throws Exception {
		WebPageBase pageBase;
		WebLoginPage villageHealthWebLoginPage;
		WebPatientsListPage villageHealthWebPatientsListPage;
		WebPatientsPage villageHealthWebPatientsPage;

		pageBase = new WebPageBase(getWebDriver(m.getName()));
		villageHealthWebLoginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("applicationURL"));
		villageHealthWebLoginPage.enterUserName(applicationProperty.getProperty("username"));
		villageHealthWebLoginPage.enterPassword(util.decodeBase64String(applicationProperty
				.getProperty("password")));
		villageHealthWebPatientsListPage = villageHealthWebLoginPage.clickLogin();
		villageHealthWebPatientsPage = villageHealthWebPatientsListPage.selectAPatient("chen"
				+ ", " + "paige");
		villageHealthWebPatientsPage.clickAssignReminders();
		villageHealthWebPatientsPage.clickRecurringCheckBox();
		villageHealthWebPatientsPage.clickRecurringFilter();
		villageHealthWebPatientsPage.selectRecurringOption("Weekly");

		villageHealthWebPatientsPage.selectAWeeklyReminderDay("S");

		villageHealthWebPatientsPage.selectWeeklyReminderStartDate("22");
		villageHealthWebPatientsPage.selectWeeklyReminderEndDate("30");

		villageHealthWebPatientsPage.enterReminderTitle("this is automated reminder");
		villageHealthWebPatientsPage.enterReminderDescription("this is automated reminder");

		villageHealthWebPatientsPage.clickReminderFlag();

		villageHealthWebPatientsPage.clickAssign();

		Assert.assertEquals(villageHealthWebPatientsPage.getReminderSuccessMessage(),
				"Reminder created successfully");

		villageHealthWebPatientsPage.clickOk();

		villageHealthWebPatientsPage.clickSignOut();
	}

	@Test
	public void smokeTest8(Method m) throws Exception {
		WebPageBase pageBase;
		WebLoginPage villageHealthWebLoginPage;
		WebPatientsListPage villageHealthWebPatientsListPage;
		WebPatientsPage villageHealthWebPatientsPage;

		pageBase = new WebPageBase(getWebDriver(m.getName()));
		villageHealthWebLoginPage = (WebLoginPage) pageBase
				.navigateTo(applicationProperty.getProperty("applicationURL"));
		villageHealthWebLoginPage.enterUserName(applicationProperty.getProperty("username"));
		villageHealthWebLoginPage.enterPassword(util.decodeBase64String(applicationProperty
				.getProperty("password")));
		villageHealthWebPatientsListPage = villageHealthWebLoginPage.clickLogin();
		villageHealthWebPatientsPage = villageHealthWebPatientsListPage.selectAPatient("chen"
				+ ", " + "paige");
		villageHealthWebPatientsPage.clickAssignReminders();
		villageHealthWebPatientsPage.clickRecurringCheckBox();
		villageHealthWebPatientsPage.clickRecurringFilter();
		villageHealthWebPatientsPage.selectRecurringOption("Monthly");

		villageHealthWebPatientsPage.selectDateOfMonth("20");
		villageHealthWebPatientsPage.startingMonth("January");
		villageHealthWebPatientsPage.endingMonth("March");

		villageHealthWebPatientsPage.enterReminderTitle("this is automated reminder");
		villageHealthWebPatientsPage.enterReminderDescription("this is automated reminder");

		villageHealthWebPatientsPage.clickReminderFlag();

		villageHealthWebPatientsPage.clickAssign();

		Assert.assertEquals(villageHealthWebPatientsPage.getReminderSuccessMessage(),
				"Reminder created successfully");

		villageHealthWebPatientsPage.clickOk();

		villageHealthWebPatientsPage.clickSignOut();
	}
}