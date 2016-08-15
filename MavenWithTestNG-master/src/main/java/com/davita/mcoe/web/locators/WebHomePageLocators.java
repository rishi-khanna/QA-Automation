package com.davita.mcoe.web.locators;

import org.openqa.selenium.By;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public final class WebHomePageLocators {

	public static final By WAITINGROOM = By.linkText("Waiting Room");
	public static final By PATIENTS = By.linkText("Patients");
	public static final By ACCOUNTS = By.linkText("Accounts");
	public static final By RESOURCES = By.linkText("Resources");
	public static final By VILLAGEHEALTH = By.linkText("Patient Engagement");
	public static final By ADDRESSBOOK = By.className("davita-patient-name");
	public static final By ADDNEW = By.linkText("Add New");
	public static final By REFRESHRESOURCES = By.linkText("Refresh Resources");
	public static final By SIGNOUT = By.linkText("Sign Out");
	public static final By BYPATIENTLIST = By
			.xpath("//div[@id='contentContainer']/div[2]/div[2]/ul/li/span[1]/a[1]");
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
	public static final By BYCANCEL = By.linkText("Cancel");
}
