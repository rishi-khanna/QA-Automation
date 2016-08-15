package com.davita.mcoe.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.davita.mcoe.exceptions.HostNotReachableException;
import com.davita.mcoe.utilities.Logg;
import com.davita.mcoe.utilities.Utilities;

public class HTTPClient {

	private static HttpURLConnection httpConn;
	private static final Logger LOGGER = Logg.createLogger();

	/**
	 * Makes an HTTP request using GET method to the specified URL.
	 *
	 * @param requestURL
	 *            the URL of the remote server
	 * @return An HttpURLConnection object
	 * @throws IOException
	 *             thrown if any I/O error occurred
	 */
	public static HttpURLConnection sendGetRequest(String requestURL) throws IOException {
		URL url = new URL(requestURL);
		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setUseCaches(false);

		httpConn.setDoInput(true); // true if we want to read server's response
		httpConn.setDoOutput(false); // false indicates this is a GET request

		return httpConn;
	}

	/**
	 * Makes an HTTP request using POST method to the specified URL.
	 *
	 * @param requestURL
	 *            the URL of the remote server
	 * @param params
	 *            A map containing POST data in form of key-value pairs
	 * @return An HttpURLConnection object
	 * @throws IOException
	 *             thrown if any I/O error occurred
	 */
	public static HttpURLConnection sendPostRequest(String requestURL, Map<String, String> params, String accessToken)
			throws IOException {
		URL url = new URL(requestURL);
		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setUseCaches(false);
		httpConn.setRequestProperty("AccessToken", accessToken);
		httpConn.setDoInput(true); // true indicates the server returns response

		StringBuffer requestParams = new StringBuffer();

		if (params != null && params.size() > 0) {

			httpConn.setDoOutput(true); // true indicates POST request

			// creates the params string, encode them using URLEncoder
			Iterator<String> paramIterator = params.keySet().iterator();
			while (paramIterator.hasNext()) {
				String key = paramIterator.next();
				String value = params.get(key);
				requestParams.append(URLEncoder.encode(key, "UTF-8"));
				requestParams.append("=").append(URLEncoder.encode(value, "UTF-8"));
				requestParams.append("&");
			}

			// sends POST data
			OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
			writer.write(requestParams.toString());
			writer.flush();
		}

		return httpConn;
	}

	/**
	 * Makes an HTTP request using POST method to the specified URL.
	 *
	 * @param requestURL
	 *            the URL of the remote server
	 * @param params
	 *            A map containing POST data in form of key-value pairs
	 * @return An HttpURLConnection object
	 * @throws IOException
	 *             thrown if any I/O error occurred
	 */
	public static HttpURLConnection sendDeleteRequest(String requestURL) throws IOException {
		URL url = new URL(requestURL);
		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setUseCaches(false);
		httpConn.setRequestMethod("DELETE");
		httpConn.setRequestProperty("X-Authorization", "P@2i3nt");
		httpConn.setDoInput(true); // true indicates the server returns response
		return httpConn;
	}

	public static HttpURLConnection sendDeleteRequest1(String requestURL, String accessToken) throws IOException {
		URL url = new URL(requestURL);
		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setUseCaches(false);
		httpConn.setRequestMethod("DELETE");
		httpConn.setRequestProperty("AccessToken", accessToken);
		// httpConn.setRequestProperty("X-Authorization", "P@2i3nt");
		httpConn.setDoInput(true); // true indicates the server returns response
		return httpConn;
	}

	/**
	 * Makes an HTTP request using POST method to the specified URL.
	 *
	 * @param requestURL
	 *            the URL of the remote server
	 * @param params
	 *            A map containing POST data in form of key-value pairs
	 * @return An HttpURLConnection object
	 * @throws IOException
	 *             thrown if any I/O error occurred
	 */
	public static HttpURLConnection sendPutRequest(String requestURL, String accessToken) throws IOException {
		URL url = new URL("https://pe-dev-vh.mcoe.davita.com/api/Patients/301/Resources/2861/Recommend");
		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setDoOutput(true);
		httpConn.setRequestMethod("PUT");
		httpConn.setRequestProperty("AccessToken", accessToken);

		OutputStreamWriter out = new OutputStreamWriter(httpConn.getOutputStream());
		out.write("Resource content");
		out.close();

		return httpConn;
	}

	/**
	 * Returns only one line from the server's response. This method should be
	 * used if the server returns only a single line of String.
	 *
	 * @return a String of the server's response
	 * @throws IOException
	 *             thrown if any I/O error occurred
	 */
	public static String readSingleLineRespone(int expectedResponseCode) throws IOException {
		InputStream inputStream = null;
		if (httpConn != null) {
			if (httpConn.getResponseCode() != expectedResponseCode) {
				inputStream = httpConn.getErrorStream();
			} else {
				inputStream = httpConn.getInputStream();
			}
		} else {
			throw new IOException("Connection is not established.");
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		String response = reader.readLine();
		reader.close();

		return response;
	}

	/**
	 * Returns an array of lines from the server's response. This method should
	 * be used if the server returns multiple lines of String.
	 * 
	 * @param expectedResponseCode
	 *
	 * @return an array of Strings of the server's response
	 * @throws IOException
	 *             thrown if any I/O error occurred
	 */
	public static String readMultipleLinesRespone(int expectedResponseCode) throws IOException {
		InputStream inputStream = null;

		if (httpConn != null) {
			if (httpConn.getResponseCode() != expectedResponseCode) {
				inputStream = httpConn.getErrorStream();
			} else {
				inputStream = httpConn.getInputStream();
			}
		} else {
			throw new IOException("Connection is not established.");
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		String jsonString = "";
		String temp;
		while ((temp = reader.readLine()) != null) {
			jsonString += temp + "\n";
		}
		reader.close();

		return jsonString;
	}

	/**
	 * Closes the connection if opened
	 */
	public static void disconnect() {
		if (httpConn != null) {
			httpConn.disconnect();
		}
	}

	public static Object readJSON(Object json, String searchKey) {
		String jsonString;
		JSONObject jsonObject = null;

		if (json instanceof String) {
			jsonString = (String) json;
			jsonObject = new JSONObject(jsonString);
		} else if (json instanceof JSONObject) {
			jsonObject = (JSONObject) json;
		}

		LOGGER.info(Utilities.getCurrentThreadId() + jsonObject.toString(4));
		Iterator<String> iterator = jsonObject.keys();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if (searchKey.equals(key)) {
				return jsonObject.get(key);
			}
		}
		return null;
	}

	public static boolean isHostReachable(String host) throws HostNotReachableException {
		HttpURLConnection connection = null;
		int responseCode;
		try {
			connection = (HttpURLConnection) new URL(host).openConnection();
			connection.setRequestMethod("HEAD");
			responseCode = connection.getResponseCode();
		} catch (IOException io) {
			HostNotReachableException hostNotReachableException = new HostNotReachableException("URL:" + host
					+ " is not reachable. " + "Please check VPN connection, which maybe required for some URLs");
			LOGGER.error(Utilities.getCurrentThreadId() + "Exception in isHostReachable() Method",
					hostNotReachableException);
			throw hostNotReachableException;
		}
		if (responseCode != 200) {
			LOGGER.error(Utilities.getCurrentThreadId() + "Response Code is " + responseCode + " for URL:" + host);
			return false;
		} else {
			LOGGER.info(Utilities.getCurrentThreadId() + "Valid Response Code for URL:" + host);
			return true;
		}
	}
}
