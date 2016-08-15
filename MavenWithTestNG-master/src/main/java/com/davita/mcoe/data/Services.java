package com.davita.mcoe.data;

import org.apache.log4j.Logger;

import com.davita.mcoe.exceptions.ResponceCodeMismatchException;
import com.davita.mcoe.extendable.locator.AppConfig;
import com.davita.mcoe.services.HTTPClient;
import com.davita.mcoe.utilities.Logg;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class Services {

	private static final Logger LOGGER = Logg.createLogger();

	public enum URI {

		HomeDev("https://pe-dev-home.mcoe.davita.com/api"), IndiaDev(
				"https://pe-dev-india.mcoe.davita.com/api"), VillageHealthDev("https://pe-dev-vh.mcoe.davita.com/api"),

		HomeQA("https://pe-qa-home.mcoe.davita.com/api"), IndiaQA(
				"https://pe-qa-india.mcoe.davita.com/api"), VillageHealthQA("https://pe-qa-vh.mcoe.davita.com/api"),

		HomeUAT("https://pe-uat-home.mcoe.davita.com/api"), IndiaUAT(
				"https://pe-uat-india.mcoe.davita.com/api"), VillageHealthUAT("https://pe-uat-vh.mcoe.davita.com/api");

		private String uri;

		URI(String uri) {
			this.uri = uri;
		}

		public String getURL() {
			return uri;
		}
	}

	public enum EndPoints {

		// Enums are static and final by default, hence all of them will be
		// initialized at once
		DeleteUserAccount("/Accounts/"), RegisterUser("/Accounts/Register"), ClinicianLogin(
				"/Accounts/ADLogin"), Reminders(
						"/Reminders"), DeleteReminders("/Patients/" + Patient.getId() + "/Reminders/");

		private String endPoint;

		EndPoints(String endPoint) {

			String uri;

			if (AppConfig.getAppEnvirnonment().equals("DEV")) {
				if (AppConfig.getAppName().equals("HOME")) {
					uri = URI.HomeDev.getURL();
				} else if (AppConfig.getAppName().equals("INDIA")) {
					uri = URI.IndiaDev.getURL();
				} else {
					uri = URI.VillageHealthDev.getURL();
				}
			} else if (AppConfig.getAppEnvirnonment().equals("QA")) {
				if (AppConfig.getAppName().equals("HOME")) {
					uri = URI.HomeQA.getURL();
				} else if (AppConfig.getAppName().equals("INDIA")) {
					uri = URI.IndiaQA.getURL();
				} else {
					uri = URI.VillageHealthQA.getURL();
				}
			} else {
				if (AppConfig.getAppName().equals("HOME")) {
					uri = URI.HomeUAT.getURL();
				} else if (AppConfig.getAppName().equals("INDIA")) {
					uri = URI.IndiaUAT.getURL();
				} else {
					uri = URI.VillageHealthUAT.getURL();
				}
			}

			if (AppConfig.getAppName().equals("INDIA") && endPoint.equals("/Accounts/ADLogin")) {
				this.endPoint = uri + "/Accounts/IndiaLogin";
			} else {
				System.out.println(uri + endPoint);
				this.endPoint = uri + endPoint;
			}
		}

		public String getFullyResolvedURL(String encryptedUserName) {
			System.out.println(endPoint + encryptedUserName);
			String completeUrl = endPoint + encryptedUserName;
			return completeUrl;
		}

		public String getFullyResolvedURL() {
			return endPoint;
		}
	}
}