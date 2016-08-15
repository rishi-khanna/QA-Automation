package com.davita.mcoe.web.locators;

import org.openqa.selenium.By;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public final class WebPatientsPageLocators {

	// Patient Info
	public static final By BYPATIENTNAME = By.xpath("//span[@class='patient-information']/p");
	public static final By BYID = By.xpath("//span[@class='patient-id']/p[1]");
	public static final By BYEMAILADDRESS = By.xpath("//span[@class='patient-id']/p[2]");
	public static final By BYPHONENUMBER = By.xpath("//span[@class='patient-id']/p[3]");

	// Patient DOB
	public static final By BYDOB = By.xpath("//span[@class='patient-dob']/p[1]");
	public static final By BYAGE = By.xpath("//span[@class='patient-dob']/p[2]");

	// buttons
	public static final By BYASSIGNRESOURCE = By.cssSelector(".assign-content");
	public static final By BYASSIGNREMINDERATTOP = By.cssSelector(".assign-reminder");
	public static final By BYASSIGNREMINDER = By.cssSelector(".assign-reminder.float-left");
	public static final By BYFILTER = By.cssSelector(".content-filter");
	public static final By BYSEARCHBAR = By.cssSelector(".search-field-s");

	// Reminders Tab
	public static final By BYAPPTS = By.linkText("Appts.");
	public static final By BYMEDS = By.linkText("Meds");
	public static final By BYSELFCARE = By.linkText("Self Care");
	public static final By BYLABS = By.linkText("Labs");
	public static final By BYNUTRITION = By.linkText("Nutrition");

	public static final By BYRECURRING = By.cssSelector(".recurring-reminder>a");
	public static final By BYRECURRINGFILTER = By.cssSelector(".content-filter.recurring-selector");
	public static final By BYRECURRINGFILTEROPTIONS = By
			.cssSelector(".content-filter.recurring-selector>ul>li>a");

	public static final By BYREMINDERSTARTDATE = By.cssSelector("#start-date-daily+div+a");
	public static final By BYREMINDERENDDATE = By.cssSelector("#end-date-daily+div+a");

	public static final By BYREMINDERTITLE = By.id("txt-reminder-title");
	public static final By BYREMINDERDESCRIPTION = By.name("reminderdesc");
	public static final By BYREMINDERFLAG = By.cssSelector(".reminder-flag");

	public static final By BYWEEKLYREMINDERDAY = By.cssSelector(".weekly-reminder li");
	public static final By BYWEEKLYREMINDERSTARTDATE = By.cssSelector("#start-date-weekly+div+a");
	public static final By BYWEEKLYREMINDERENDDATE = By.cssSelector("#end-date-weekly+div+a");

	public static final By BYMONTHLYREMINDERDATEDROPDOWN = By
			.cssSelector(".monthly-reminder>div>div[dropdown-model='recurringMonthlyDeliveryDay']");
	public static final By BYMONTHLYREMINDERDATES = By
			.cssSelector(".monthly-reminder>div>div>ul>li>a");
	public static final By BYSTARTINGMONTHDROPDOWN = By.cssSelector(".start-month");
	public static final By BYSTARTINGMONTH = By.cssSelector(".start-month li");
	public static final By BYENDINGMONTHDROPDOWN = By.cssSelector(".end-month");
	public static final By BYENDINGMONTH = By.cssSelector(".end-month li");

	public static final By BYASSIGNRESOURCES = By.linkText("Assign Resource");
	public static final By BYSELECTFIRSTRESOURCE = By
			.xpath("//div[@class='load-unassign-resource']/ul/li[1]/div[1]/span");
	public static final By BYMAKETHISRESOURCEASREMINDER = By
			.linkText("Make this Resource a Reminder");
	public static final By BYDELIVERYDATE = By.id("due-date-input");
	public static final By BYREMINDERAMPM = By.id("reminderAMPM");
	public static final By BYREMINDERMINUTES = By.id("reminderMinutes");
	public static final By BYREMINDERHOUR = By.id("reminderHour");
	public static final By BYREMINDERTIMEZONE = By.id("reminderTimezone");
	public static final By BYSELECTENABLEDDATE = By
			.xpath("//div[@class='datepicker-calendar-body']/a[not(contains(@class,'datepicker-disabled'))]");
	public static final By BYASSIGN = By.linkText("Assign");
	public static final By BYOK = By.linkText("OK");

	public static final By BYREMINDERSUCCESSMESSAGE = By.xpath("//div[@id='msgpopup']/p[1]");
	public static final By BYCANCEL = By.linkText("Cancel");
}
