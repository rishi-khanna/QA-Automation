package com.davita.mcoe.page.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.davita.mcoe.actions.MobileActions;
import com.davita.mcoe.exceptions.WaitException;
import com.davita.mcoe.property.reader.PropertyManager;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class MobilePageBase extends PageBase {

	protected WebDriver driver;
	protected MobileActions mobileActions;
	protected static final Properties frameworkProperty = PropertyManager
			.loadFrameworkPropertyFile("framework.properties");
	
	public MobilePageBase(WebDriver driver) throws WaitException {
		this.driver = driver;
		this.mobileActions = new MobileActions(driver);
	}
}
