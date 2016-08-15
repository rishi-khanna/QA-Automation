package com.davita.mcoe.mobile;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.davita.mcoe.data.Clinician;
import com.davita.mcoe.data.Patient;
import com.davita.mcoe.data.Reminders;
import com.davita.mcoe.data.Services.EndPoints;
import com.davita.mcoe.data.Services.URI;
import com.davita.mcoe.data.Reminders;
import com.davita.mcoe.exceptions.WaitException;
import com.davita.mcoe.extendable.locator.AppConfig;
import com.davita.mcoe.mobile.pages.MobileHomePage;
import com.davita.mcoe.mobile.pages.MobileLoginPage;
import com.davita.mcoe.mobile.pages.MobileRemindersPage;
import com.davita.mcoe.mobile.pages.MobileTermsAndConditionsPage;
import com.davita.mcoe.mobile.pages.MobileContactNursePage;
import com.davita.mcoe.mobile.pages.MobileHomePage;
import com.davita.mcoe.mobile.pages.MobileLoginPage;
import com.davita.mcoe.mobile.pages.MobileRemindersPage;
import com.davita.mcoe.mobile.pages.MobileResourcesPage;
import com.davita.mcoe.mobile.pages.MobileSettingsPage;
import com.davita.mcoe.mobile.pages.MobileTermsAndConditionsPage;
import com.davita.mcoe.services.PEServices;
import com.davita.mcoe.test.base.TestBase;
import com.davita.mcoe.utilities.DateAndTime;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:DaVita
 */
public class MobileReminders extends TestBase {

	@Test(groups = "reminders")
	public void validateVillageHealthMobileCreateReminders(Method m) throws Exception {

		MobileLoginPage mobileLoginPage;
		MobileHomePage mobileHomePage;
		MobileRemindersPage mobileRemindersPage;
		MobileTermsAndConditionsPage mobileTermsAndConditions;

		PEServices.deleteUserAccount(EndPoints.DeleteUserAccount.getFullyResolvedURL("cmlzaGlrdGVzdA=="));
		
		PEServices.registerPatient(Patient.getId(), EndPoints.RegisterUser.getFullyResolvedURL());

		PEServices.ADLoginPortal(EndPoints.ClinicianLogin.getFullyResolvedURL(), Clinician.getEncryptedUserName(),
				 Clinician.getEncryptedPassword());

		PEServices.createReminderWithIdForPatientId(
				EndPoints.Reminders.getFullyResolvedURL(), 
				Patient.getId(), Reminders.Meds.getReminderId(),
				Reminders.Meds.getDueDate(), "false", Reminders.Meds.getTitle(),
				Reminders.Meds.getDescription(), "MST", "false");

		PEServices.createReminderWithIdForPatientId(
				EndPoints.Reminders.getFullyResolvedURL(), 
				Patient.getId(), Reminders.Labs.getReminderId(),
				Reminders.Labs.getDueDate(), "false", Reminders.Labs.getTitle(),
				Reminders.Labs.getDescription(), "MST", "false");

		PEServices.createReminderWithIdForPatientId(
				EndPoints.Reminders.getFullyResolvedURL(), 
				Patient.getId(), Reminders.Appointment.getReminderId(),
				Reminders.Appointment.getDueDate(), "false", Reminders.Appointment.getTitle(),
				Reminders.Appointment.getDescription(), "MST", "false");
		
		PEServices.createReminderWithIdForPatientId(
				EndPoints.Reminders.getFullyResolvedURL(), 
				Patient.getId(), Reminders.SelfCare.getReminderId(),
				Reminders.SelfCare.getDueDate(), "false", Reminders.SelfCare.getTitle(),
				Reminders.SelfCare.getDescription(), "MST", "false");

		PEServices.createReminderWithIdForPatientId(
				EndPoints.Reminders.getFullyResolvedURL(), 
				Patient.getId(), Reminders.Nutrition.getReminderId(),
				Reminders.Nutrition.getDueDate(), "false", Reminders.Nutrition.getTitle(),
				Reminders.Nutrition.getDescription(), "MST", "false");

		mobileLoginPage = new MobileLoginPage(getMobileDriver(m.getName()));

		if ("iOS".equals(AppConfig.getPlatform())) {
			mobileLoginPage.acceptLoginPageNotification();
			mobileLoginPage.tapDEVButton();
			mobileLoginPage.changeEnvironmentTo("DEV");
		}

		mobileLoginPage.showKeyboard();
		mobileLoginPage.enterUserName("rishiktest");
		mobileLoginPage.enterPassword("tester123");

		if ("iOS".equals(AppConfig.getPlatform())) {
			mobileLoginPage.clickDoneOnKeyboardToolBar();
		}else{
			mobileLoginPage.hideKeyboard();
			mobileLoginPage.tapDEVButton();
			mobileLoginPage.changeEnvironmentTo("DEV");
		}

		mobileTermsAndConditions = mobileLoginPage.tapLogin();
		
		if ("iOS".equals(AppConfig.getPlatform())) {
			mobileHomePage = mobileTermsAndConditions.acceptTermsAndConditions();
		}
		else{
			mobileHomePage = mobileLoginPage.hackToGetAroundTermsAndConditionsInAndroid();
		}
		mobileRemindersPage = mobileHomePage.tapReminders();
		
		Assert.assertNotNull(mobileRemindersPage.scrollToText(Reminders.Appointment.getTitle()));
		Assert.assertNotNull(mobileRemindersPage.scrollToText(Reminders.Appointment.getDescription()));
		
		Assert.assertNotNull(mobileRemindersPage.scrollToText(Reminders.Labs.getTitle()));
		Assert.assertNotNull(mobileRemindersPage.scrollToText(Reminders.Labs.getDescription()));
		
		Assert.assertNotNull(mobileRemindersPage.scrollToText(Reminders.Meds.getTitle()));
		Assert.assertNotNull(mobileRemindersPage.scrollToText(Reminders.Meds.getDescription()));
		
		Assert.assertNotNull(mobileRemindersPage.scrollToText(Reminders.Nutrition.getTitle()));
		Assert.assertNotNull(mobileRemindersPage.scrollToText(Reminders.Nutrition.getDescription()));
		
		Assert.assertNotNull(mobileRemindersPage.scrollToText(Reminders.SelfCare.getTitle()));
		Assert.assertNotNull(mobileRemindersPage.scrollToText(Reminders.SelfCare.getDescription()));
	}

	@Test(groups = "reminders")
	public void validateVillageHealthMobileDeleteReminders(Method m) throws Exception {

		MobileLoginPage mobileLoginPage;
		MobileHomePage mobileHomePage;
		MobileRemindersPage mobileRemindersPage;
		MobileTermsAndConditionsPage mobileTermsAndConditions;
			
		PEServices.deleteUserAccount(EndPoints.DeleteUserAccount.getFullyResolvedURL("cmlzaGlrdGVzdA=="));
		
		PEServices.registerPatient(Patient.getId(), EndPoints.RegisterUser.getFullyResolvedURL());
		
		PEServices.ADLoginPortal(EndPoints.ClinicianLogin.getFullyResolvedURL(), Clinician.getEncryptedUserName(),
				 Clinician.getEncryptedPassword());

		int reminderId = PEServices.createReminderWithIdForPatientId(
				EndPoints.Reminders.getFullyResolvedURL(), 
				Patient.getId(), Reminders.Meds.getReminderId(),
				Reminders.Meds.getDueDate(), "false", Reminders.Meds.getTitle(),
				Reminders.Meds.getDescription(), "MST", "false");

		mobileLoginPage = new MobileLoginPage(getMobileDriver(m.getName()));
		
		if ("iOS".equals(AppConfig.getPlatform())) {
			mobileLoginPage.acceptLoginPageNotification();
			//mobileLoginPage.tapDEVButton();
			//mobileLoginPage.changeEnvironmentTo("DEV");
		}
		
		mobileLoginPage.enterUserName("rishiktest");
		mobileLoginPage.enterPassword("tester123");
		
		if ("iOS".equals(AppConfig.getPlatform())) {
			mobileLoginPage.clickDoneOnKeyboardToolBar();
		} else{
			mobileLoginPage.hideKeyboard();
			mobileLoginPage.tapDEVButton();
			mobileLoginPage.changeEnvironmentTo("DEV");
		}

		mobileTermsAndConditions = mobileLoginPage.tapLogin();//locator issue on ios
		if ("iOS".equals(AppConfig.getPlatform())) {
			mobileHomePage = mobileTermsAndConditions.acceptTermsAndConditions();
		}
		else{
			mobileHomePage = mobileLoginPage.hackToGetAroundTermsAndConditionsInAndroid();
		}
		
		mobileRemindersPage = mobileHomePage.tapReminders();

		//asserts
		Assert.assertEquals(
				mobileRemindersPage.getTitleOfReminder().get(0),Reminders.Meds.getTitle());

		PEServices.deleteReminderWithId(reminderId, EndPoints.DeleteReminders.getFullyResolvedURL());
		mobileRemindersPage.refreshRemindersPage();

		String newLineIgnoredEmptyRemindersListFirstMessage;
		if(mobileRemindersPage.getEmptyRemindersListFirstMessage().contains("\n")){
			newLineIgnoredEmptyRemindersListFirstMessage = mobileRemindersPage.getEmptyRemindersListFirstMessage().replace("\n ", "");
		}
		else{
			newLineIgnoredEmptyRemindersListFirstMessage = mobileRemindersPage.getEmptyRemindersListFirstMessage();
		}
		
		Assert.assertEquals(newLineIgnoredEmptyRemindersListFirstMessage,"You don’t have any reminders yet!");
		Assert.assertEquals(mobileRemindersPage.getEmptyRemindersListSecondMessage(),
				"When your clinician assigns reminders to you, they will appear here.");
	}
	
	@Test(groups = "reminders")
	public void validateAcknowledgeAndUndoReminder(Method m) throws Exception {

		MobileLoginPage mobileLoginPage;
		MobileHomePage mobileHomePage;
		MobileRemindersPage mobileRemindersPage;
		MobileTermsAndConditionsPage mobileTermsAndConditions;
		
		
		PEServices.deleteUserAccount(EndPoints.DeleteUserAccount.getFullyResolvedURL("cmlzaGlrdGVzdA=="));
		
		PEServices.registerPatient(Patient.getId(), EndPoints.RegisterUser.getFullyResolvedURL());
		
		PEServices.ADLoginPortal(EndPoints.ClinicianLogin.getFullyResolvedURL(), Clinician.getEncryptedUserName(),
				 Clinician.getEncryptedPassword());

		PEServices.createReminderWithIdForPatientId(
				EndPoints.Reminders.getFullyResolvedURL(), 
				Patient.getId(), Reminders.Meds.getReminderId(),
				Reminders.Meds.getDueDate(), "false", Reminders.Meds.getTitle(),
				Reminders.Meds.getDescription(), "MST", "false");

		mobileLoginPage = new MobileLoginPage(getMobileDriver(m.getName()));
		
		if ("iOS".equals(AppConfig.getPlatform())) {
			mobileLoginPage.acceptLoginPageNotification();
		}
		
		mobileLoginPage.enterUserName("rishiktest");
		mobileLoginPage.enterPassword("tester123");
		
		if ("iOS".equals(AppConfig.getPlatform())) {
			mobileLoginPage.clickDoneOnKeyboardToolBar();
		} else{
			mobileLoginPage.hideKeyboard();
			mobileLoginPage.tapDEVButton();
			mobileLoginPage.changeEnvironmentTo("DEV");
		}

		mobileTermsAndConditions = mobileLoginPage.tapLogin();//locator issue on ios
		if ("iOS".equals(AppConfig.getPlatform())) {
			mobileHomePage = mobileTermsAndConditions.acceptTermsAndConditions();
		}
		else{
			mobileHomePage = mobileLoginPage.hackToGetAroundTermsAndConditionsInAndroid();
		}
		
		mobileRemindersPage = mobileHomePage.tapReminders();
		
		Assert.assertNotNull(mobileRemindersPage.scrollToText(Reminders.Meds.getTitle()));
		Assert.assertNotNull(mobileRemindersPage.scrollToText(Reminders.Meds.getDescription()));
		
		mobileRemindersPage.tapToAcknowledgeReminder();
		
		mobileRemindersPage.waitFor5secsForReminderToBeAcknowledged();
		
		mobileRemindersPage.tapRemindersCompletedTab();
		
		Assert.assertNotNull(mobileRemindersPage.scrollToText(Reminders.Meds.getTitle()));
		Assert.assertNotNull(mobileRemindersPage.scrollToText(Reminders.Meds.getDescription()));
			
		mobileRemindersPage.tapToUndoAcknowledgeReminder();
		
		mobileRemindersPage.acceptUndoCompleteReminderDialogue();
		
		mobileRemindersPage.tapReminderToDoTab();
		
		Assert.assertNotNull(mobileRemindersPage.scrollToText(Reminders.Meds.getTitle()));
		Assert.assertNotNull(mobileRemindersPage.scrollToText(Reminders.Meds.getDescription()));
	}
	
}