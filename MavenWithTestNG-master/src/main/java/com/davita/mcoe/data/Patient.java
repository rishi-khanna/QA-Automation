package com.davita.mcoe.data;

import com.davita.mcoe.extendable.locator.AppConfig;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class Patient {
	
    public static String getId() {
    	
    	String patientId;
    	
    	if(AppConfig.getAppEnvirnonment().equals("DEV")){
			if(AppConfig.getAppName().equals("HOME")){
				patientId= "283";
			}
			else if(AppConfig.getAppName().equals("INDIA")){
				patientId="1534";
			}
			else{
				patientId="301";
			}
		}else if(AppConfig.getAppEnvirnonment().equals("QA")){
			throw new RuntimeException("Patient Id functionality not yet implemented for QA");
		}else {
			throw new RuntimeException("Patient Id functionality not yet implemented for UAT");
		}
        return patientId;
    }
}
