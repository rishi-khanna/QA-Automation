package com.davita.mcoe.web.pages;

import static com.davita.mcoe.web.locators.WebPatientsPageLocators.*;

import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import com.davita.mcoe.exceptions.WaitException;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class WebPatientsPage extends
		WebGlobalMenuPage {

	public WebPatientsPage(WebDriver driver) throws WaitException {
		super(driver);
		// Assert.assertTrue(webActions.getVisibiltyOfElementLocatedBy(VILLAGEHEALTH));
	}

	public String readPatientName() throws TimeoutException, WaitException {
		return webActions.getText(VISIBILITY, BYPATIENTNAME);
	}

	public String readPatientId() throws TimeoutException, WaitException {
		return webActions.getText(VISIBILITY, BYID);
	}

	public String readPatientEmailId() throws TimeoutException, WaitException {
		return webActions.getText(VISIBILITY, BYEMAILADDRESS);
	}

	public String readPatientPhone() throws TimeoutException, WaitException {
		return webActions.getText(VISIBILITY, BYPHONENUMBER);
	}

	public String readPatientDOB() throws TimeoutException, WaitException {
		return webActions.getText(VISIBILITY, BYDOB);
	}

	public String readPatientAge() throws TimeoutException, WaitException {
		return webActions.getText(VISIBILITY, BYAGE);
	}

	public WebPatientsPage clickAssignResource()
			throws TimeoutException, WaitException {
		webActions.click(VISIBILITY, BYASSIGNRESOURCES);
		return this;
	}

	public WebPatientsPage selectFirstResource()
			throws TimeoutException, WaitException {
		webActions.click(VISIBILITY, BYSELECTFIRSTRESOURCE);
		return this;
	}

	public WebPatientsPage clickMakeThisResourceAsReminder()
			throws TimeoutException, WaitException {
		webActions.click(VISIBILITY, BYMAKETHISRESOURCEASREMINDER);
		return this;
	}

	public WebPatientsPage selectDueDate(String date)
			throws TimeoutException, WaitException, InterruptedException {
		webActions.timedClick(VISIBILITY, BYDELIVERYDATE, 2000);
		webActions.selectOption(PRESENCE, BYSELECTENABLEDDATE, date);
		return this;
	}

	public WebPatientsPage selectDueDateTime(String hour,
			String minutes, String ampm, String timeZone)
			throws TimeoutException, WaitException, InterruptedException {
		webActions.selectFromDropDown(VISIBILITY, BYREMINDERHOUR, hour);
		webActions.selectFromDropDown(VISIBILITY, BYREMINDERMINUTES, minutes);
		webActions.selectFromDropDown(VISIBILITY, BYREMINDERAMPM, ampm);
		webActions.selectFromDropDown(VISIBILITY, BYREMINDERTIMEZONE, timeZone);
		return this;
	}

	public WebPatientsPage clickAssign() throws TimeoutException,
			WaitException {
		webActions.click(VISIBILITY, BYASSIGN);
		return this;
	}

	public WebPatientsPage clickOk() throws TimeoutException,
			WaitException {
		webActions.click(VISIBILITY, BYOK);
		return this;
	}

	public String getFirstResourceName() throws TimeoutException, WaitException {
		return webActions.getText(VISIBILITY, BYSELECTFIRSTRESOURCE);
	}

	public boolean validatePatientName(String patientName)
			throws TimeoutException, WaitException {
		return COMPARE.compareExactText(readPatientName(), patientName);
	}

	public boolean comparePatientID(String id) throws TimeoutException,
			WaitException {
		return COMPARE.compareExactText(readPatientId(), id);
	}

	public boolean comparePatientEmailId(String emailId)
			throws TimeoutException, WaitException {
		return COMPARE.compareExactText(readPatientEmailId(), emailId);
	}

	public boolean comparePatientPhoneNumber(String phoneNumber)
			throws TimeoutException, WaitException {
		return COMPARE.compareExactText(readPatientPhone(), phoneNumber);
	}

	public boolean comparePatientDOB(String dob) throws TimeoutException,
			WaitException {
		return COMPARE.compareExactText(readPatientDOB(), dob);
	}

	public boolean comparePatientAge(String age) throws TimeoutException,
			WaitException {
		return COMPARE.compareExactText(readPatientAge(), age);
	}

	public boolean viewAssignResourceButton() throws WaitException {
		return webActions.getVisibiltyOfElementLocatedBy(VISIBILITY,BYASSIGNRESOURCE);
	}

	public boolean viewAssignReminderButtonAtTopRight() throws WaitException {
		return webActions.getVisibiltyOfElementLocatedBy(VISIBILITY,BYASSIGNREMINDERATTOP);

	}

	public boolean viewAssignReminderButton() throws WaitException {
		return webActions.getVisibiltyOfElementLocatedBy(VISIBILITY,BYASSIGNREMINDER);
	}

	public boolean viewFilter() throws WaitException {
		return webActions.getVisibiltyOfElementLocatedBy(VISIBILITY,BYFILTER);
	}

	public boolean viewSearchBar() throws WaitException {
		return webActions.getVisibiltyOfElementLocatedBy(VISIBILITY,BYSEARCHBAR);
	}

	public void clickAssignReminders() throws TimeoutException, WaitException {
		webActions.click(VISIBILITY, BYASSIGNREMINDER);
	}

	public boolean viewApptsTab() throws WaitException {
		return webActions.getVisibiltyOfElementLocatedBy(VISIBILITY,BYAPPTS);
	}

	public boolean viewMedsTab() throws WaitException {
		return webActions.getVisibiltyOfElementLocatedBy(VISIBILITY,BYMEDS);
	}

	public boolean viewLabsTab() throws WaitException {
		return webActions.getVisibiltyOfElementLocatedBy(VISIBILITY,BYSELFCARE);
	}

	public boolean viewSelfCareTab() throws WaitException {
		return webActions.getVisibiltyOfElementLocatedBy(VISIBILITY,BYLABS);
	}

	public boolean viewNutritionTab() throws WaitException {
		return webActions.getVisibiltyOfElementLocatedBy(VISIBILITY,BYNUTRITION);
	}

	public WebPatientsPage clickMedsTab() throws TimeoutException,
			WaitException {
		webActions.click(VISIBILITY, BYMEDS);
		return this;
	}

	public void clickRecurringCheckBox() throws TimeoutException, WaitException {
		webActions.click(VISIBILITY, BYRECURRING);
	}

	public boolean viewRecurringFilter() throws WaitException {
		return webActions.getVisibiltyOfElementLocatedBy(VISIBILITY,BYRECURRINGFILTER);
	}

	public WebPatientsPage clickRecurringFilter()
			throws WaitException {
		webActions.click(VISIBILITY, BYRECURRINGFILTER);
		return this;
	}

	public boolean viewRecurringFilterOptions(
			List<String> expectedRecurringFilterOptions) throws WaitException {
		List<String> actualRecurringFilterOptions = webActions
				.getWebElementsTextInList(VISIBILITY, BYRECURRINGFILTEROPTIONS);
		return COMPARE.compareListItems(actualRecurringFilterOptions,
				expectedRecurringFilterOptions);
	}

	public void selectRecurringOption(String dropDownValue)
			throws TimeoutException, WaitException {
		webActions.selectOption(VISIBILITY, BYRECURRINGFILTEROPTIONS,
				dropDownValue);
	}

	public WebPatientsPage selectDailyReminderStartDate(
			String startDate) throws TimeoutException, WaitException,
			InterruptedException {
		webActions.timedClick(VISIBILITY, BYREMINDERSTARTDATE, 2000);
		webActions.selectOption(PRESENCE, BYSELECTENABLEDDATE, startDate);
		return this;
	}

	public WebPatientsPage selectDailyReminderEndDate(
			String endDate) throws TimeoutException, WaitException,
			InterruptedException, WebDriverException {
		webActions.timedClick(VISIBILITY, BYREMINDERENDDATE, 2000);
		webActions.selectOption(PRESENCE, BYSELECTENABLEDDATE, endDate);
		return this;
	}

	public WebPatientsPage selectWeeklyReminderStartDate(
			String startDate) throws TimeoutException, WaitException,
			InterruptedException {
		webActions.timedClick(VISIBILITY, BYWEEKLYREMINDERSTARTDATE, 2000);
		webActions.selectOption(PRESENCE, BYSELECTENABLEDDATE, startDate);
		return this;
	}

	public WebPatientsPage selectWeeklyReminderEndDate(
			String endDate) throws TimeoutException, WaitException,
			InterruptedException {
		webActions.timedClick(VISIBILITY, BYWEEKLYREMINDERENDDATE, 2000);
		webActions.selectOption(PRESENCE, BYSELECTENABLEDDATE, endDate);
		return this;
	}

	public WebPatientsPage enterReminderTitle(String title)
			throws TimeoutException, WaitException {
		webActions.enterText(VISIBILITY, BYREMINDERTITLE, title);
		return this;
	}

	public WebPatientsPage enterReminderDescription(
			String description) throws TimeoutException, WaitException {
		webActions.enterText(VISIBILITY, BYREMINDERDESCRIPTION, description);
		return this;
	}

	public WebPatientsPage clickReminderFlag()
			throws TimeoutException, WaitException {
		webActions.click(VISIBILITY, BYREMINDERFLAG);
		return this;
	}

	public WebPatientsPage selectAWeeklyReminderDay(String day)
			throws TimeoutException, WaitException {
		webActions.click(VISIBILITY, BYWEEKLYREMINDERDAY);
		return this;
	}

	public WebPatientsPage selectDateOfMonth(String date)
			throws TimeoutException, WaitException {
		webActions.click(VISIBILITY, BYMONTHLYREMINDERDATEDROPDOWN);
		webActions.selectOption(VISIBILITY, BYMONTHLYREMINDERDATES, date);
		return this;
	}

	public WebPatientsPage startingMonth(String startingMonth)
			throws TimeoutException, WaitException {
		webActions.click(VISIBILITY, BYSTARTINGMONTHDROPDOWN);
		webActions.selectOption(VISIBILITY, BYSTARTINGMONTH, startingMonth);
		return this;
	}

	public WebPatientsPage endingMonth(String endingMonth)
			throws TimeoutException, WaitException {
		webActions.click(VISIBILITY, BYENDINGMONTHDROPDOWN);
		webActions.selectOption(VISIBILITY, BYENDINGMONTH, endingMonth);
		return this;
	}

	public String getReminderSuccessMessage() throws TimeoutException,
			WaitException {
		return webActions.getText(VISIBILITY, BYREMINDERSUCCESSMESSAGE);
	}

	public WebGlobalMenuPage clickCancel()
			throws TimeoutException, WaitException, InterruptedException {
		webActions.click(VISIBILITY, BYCANCEL);
		return this;
	}
}
