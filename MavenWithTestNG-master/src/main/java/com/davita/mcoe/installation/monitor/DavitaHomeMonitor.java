package com.davita.mcoe.installation.monitor;

import java.util.List;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class DavitaHomeMonitor extends AppMonitor{

	public DavitaHomeMonitor(String app, String env) {
		super(app, env);
	}
	
	public String getBundleId() {
		if ("DEV".equals(env)) {
			return "com.davita.home.patientengagement.dev";
		} else if ("QA".equals(env)) {
			return "com.davita.home.patientengagement.qa";
		} else {
			return "com.davita.home.patientengagement.uat";
		}
	}

	public String getAppActivity() {
		return null;

	}

	public String getAppPackage() {
		return null;

	}
}
