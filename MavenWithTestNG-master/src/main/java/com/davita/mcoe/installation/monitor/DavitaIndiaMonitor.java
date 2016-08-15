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
public class DavitaIndiaMonitor extends AppMonitor{

	public DavitaIndiaMonitor(String app, String env) {
		super(app, env);
	}
	
	public String getBundleId() {
		if ("DEV".equals(env)) {
			return "com.davita.india.patientengagement.dev";
		} else if ("QA".equals(env)) {
			return "com.davita.india.patientengagement.qa";
		} else {
			return "com.davita.india.patientengagement.uat";
		}
	}

	public String getAppActivity() {
		return null;

	}

	public String getAppPackage() {
		return null;

	}
}
