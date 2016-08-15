package com.davita.mcoe.data;

import com.davita.mcoe.extendable.locator.AppConfig;

public class Clinician {

	private static String encryptedUserName;
	private static String encryptedPassword;

	public static String getEncryptedUserName() {

		if (AppConfig.getAppName().equals("HOME")) {
			encryptedUserName = "TW9iaWxldGVzdDE1";
		} else if (AppConfig.getAppName().equals("INDIA")) {
			encryptedUserName = "cmlzaGkua2hhbm5h";
		} else {
			encryptedUserName = "TW9iaWxldGVzdDE1";
		}
		return encryptedUserName;
	}

	public static String getEncryptedPassword() {

		if (AppConfig.getAppName().equals("HOME")) {
			encryptedPassword = "b3JhbmdlMzQh";
		} else if (AppConfig.getAppName().equals("INDIA")) {
			encryptedPassword = "dGVzdGVyMTIz";
		} else {
			encryptedPassword = "b3JhbmdlMzQh";
		}

		return encryptedPassword;
	}
}
