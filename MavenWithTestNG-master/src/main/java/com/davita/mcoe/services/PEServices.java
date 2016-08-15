package com.davita.mcoe.services;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.davita.mcoe.utilities.DateAndTime;
import com.davita.mcoe.utilities.Logg;
import com.davita.mcoe.utilities.Utilities;

public class PEServices {

	static String accessToken = null;
	private static final Logger LOGGER = Logg.createLogger();

	public static void registerPatient(String patientId, String requestRegisterURL) {
		// test sending POST Register request
		int expectedResponseCode = 201;
		Map<String, String> params1 = new HashMap<String, String>();
		params1.put("UserName", "rishiktest");
		params1.put("Password", "tester123");
		params1.put("PatientId", patientId);

		try {
			HTTPClient.sendPostRequest(requestRegisterURL, params1, "");
			String response = HTTPClient.readMultipleLinesRespone(expectedResponseCode);

			Object code = HTTPClient.readJSON(response, "Code");
			if (code != null) {
				int value = (Integer) code;
				if (value == 4102) {
					throw new RuntimeException((String) HTTPClient.readJSON(response, "Message"));
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		HTTPClient.disconnect();
	}

	public static String deleteUserAccount(String requestDeleteURL) {
		LOGGER.info(Utilities.getCurrentThreadId() + "API Services - Delete Patient Call");
		int expectedResponseCode = 200;
		try {
			HTTPClient.sendDeleteRequest(requestDeleteURL);
			String response = HTTPClient.readSingleLineRespone(expectedResponseCode);
			if (response.equals("true")) {
				LOGGER.info(Utilities.getCurrentThreadId() + "API Services Response - Patient Deleted \n");
				return "Patient deleted";
			} else {
				Object code = HTTPClient.readJSON(response, "Code");
				if (code != null) {
					int value = (Integer) code;
					if (value == 4200) {
						LOGGER.info(Utilities.getCurrentThreadId() + "API Services Response - Patient Already Deleted \n");
						return "Patient already deleted";
					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		HTTPClient.disconnect();
		return null;
	}

	public static void ADLoginPortal(String requestADLoginURL, String encryptedUsername, String encryptedPassword) {
		int expectedResponseCode = 200;
		Map<String, String> params = new HashMap<String, String>();
		params.put("UserName", encryptedUsername);
		params.put("Password", encryptedPassword);
		params.put("DeviceId", "-1");
		int count = 1;
		while (count < 5) {
			try {
				HTTPClient.sendPostRequest(requestADLoginURL, params, "");
				String response = HTTPClient.readMultipleLinesRespone(expectedResponseCode);
				Object code = HTTPClient.readJSON(response, "Code");
				if (code != null) {
					int value = (Integer) code;
					if (value == 4002) {
						HTTPClient.disconnect();
						count++;
						continue;
					}
				} else {
					accessToken = ((JSONObject) HTTPClient.readJSON(response, "TokenDetails")).getString("AccessToken");
					System.out.println("Access Token = " + accessToken);
					HTTPClient.disconnect();
					break;
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static int createReminderWithIdForPatientId(String requestRemindersURL, String patientId, String reminderTypeId,
			String dueDate, String isRecurring, String name, String description, String timeZone, String isImportant)
			throws Exception {
		int expectedResponseCode = 201;
		int reminderid = 0;
		
		Map<String, String> reminderparams = new HashMap<String, String>();
		reminderparams.put("PatientId", patientId);
		reminderparams.put("ReminderTypeId", reminderTypeId);
		reminderparams.put("DueDate", dueDate);
		reminderparams.put("IsRecurring", isRecurring);
		reminderparams.put("Name", name);
		reminderparams.put("Description", description);
		reminderparams.put("TimeZoneName", timeZone);
		reminderparams.put("IsImportant", isImportant);

		try {
			HTTPClient.sendPostRequest(requestRemindersURL, reminderparams, accessToken);
			String response = HTTPClient.readMultipleLinesRespone(expectedResponseCode);
			Object status = HTTPClient.readJSON(response, "Status");
			if (status != null) {
				int value = (Integer) status;
				if (value != 201) {
					throw new Exception((String) HTTPClient.readJSON(response, "Message"));
				}
				else
				{
					JSONObject data = (JSONObject)HTTPClient.readJSON(response, "Data");
					reminderid = (Integer)HTTPClient.readJSON(data, "ReminderId");
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		HTTPClient.disconnect();
		return reminderid;
	}

	public static void createResources() throws IOException {

		/*
		 * URL url = new URL(
		 * "https://pe-dev-vh.mcoe.davita.com/api/Patients/301/Resources/2861/Recommend"
		 * ); HttpURLConnection httpCon = (HttpURLConnection)
		 * url.openConnection(); httpCon.setDoOutput(true);
		 * httpCon.setRequestMethod("PUT");
		 * httpCon.setRequestProperty("AccessToken", accessToken);
		 * OutputStreamWriter out = new
		 * OutputStreamWriter(httpCon.getOutputStream()); out.write(
		 * "Resource content"); out.close(); httpCon.getInputStream();
		 * System.out.println("Response Code is" + httpCon.getResponseCode());
		 * System.out.println("Response Code is" +
		 * httpCon.getResponseMessage()); httpCon.disconnect();
		 */

		// test sending create Resources

		String assignResourcesURL = "https://pe-dev-vh.mcoe.davita.com/api/Patients/301/Resources/2861/Recommend";
		try {
			int responseCode = 200;
			HTTPClient.sendPutRequest(assignResourcesURL, accessToken);
			String response = HTTPClient.readSingleLineRespone(responseCode);

			System.out.println("Response code for resources is " + response);

		} catch (IOException e) {
			e.printStackTrace();
		}
		HTTPClient.disconnect();

	}

	public static String deleteReminderWithId(int reminderId,String requestDeleteReminderURL) throws IOException {
		int expectedResponseCode = 200;
		try {
			HTTPClient.sendDeleteRequest1(requestDeleteReminderURL+reminderId, accessToken);
			String response = HTTPClient.readSingleLineRespone(expectedResponseCode);
			if (response.equals("true")) {
				LOGGER.info(Utilities.getCurrentThreadId() + "API Services Response - Reminder Deleted");
				return "Reminder deleted";
			} else {
				Object code = HTTPClient.readJSON(response, "Code");
				if (code != null) {
					int value = (Integer) code;
					if (value == 4200) {
						LOGGER.info(Utilities.getCurrentThreadId() + "API Services Response - Reminder Already Deleted");
						return "Reminder already deleted";
					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return requestDeleteReminderURL;
	}
}
