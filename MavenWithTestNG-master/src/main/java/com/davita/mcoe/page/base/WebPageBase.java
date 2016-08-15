package com.davita.mcoe.page.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.davita.mcoe.actions.WebActions;
import com.davita.mcoe.exceptions.URLNavigationException;
import com.davita.mcoe.exceptions.WaitException;
import com.davita.mcoe.utilities.Logg;
import com.davita.mcoe.utilities.Utilities;
import com.davita.mcoe.web.pages.WebLoginPage;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class WebPageBase extends PageBase {

	protected WebDriver driver;
	protected WebActions webActions;
	protected String webStrTempVar;
	protected static final Logger log = Logg.createLogger();

	public WebPageBase(WebDriver driver) throws WaitException {
		this.driver = driver;
		this.webActions = new WebActions(driver);
	}

	public WebPageBase navigateTo(String url) throws URLNavigationException,
			WaitException {
		webActions.maximizeBrowser();
		webActions.navigateToURL(url);
			log.info(Utilities.getCurrentThreadId()
					+ "Returning the instance of Village Health Login");
			return new WebLoginPage(driver);
	}
}
