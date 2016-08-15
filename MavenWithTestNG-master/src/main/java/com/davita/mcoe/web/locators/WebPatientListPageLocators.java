package com.davita.mcoe.web.locators;

import org.openqa.selenium.By;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public final class WebPatientListPageLocators {

	public static final By PATIENTS = By.linkText("Patients");
	public static final By VILLAGEHEALTH = By.linkText("Patient Engagement");
	public static final By ADDRESSBOOK = By.className("davita-patient-name");
	public static final By ADDNEW = By.linkText("Add New");
	public static final By REFRESHRESOURCES = By.linkText("Refresh Resources");
	public static final By BYPATIENTLIST = By
			.xpath("//div[@id='contentContainer']/div[2]/div[2]/ul/li/span[1]/a[1]");

}
