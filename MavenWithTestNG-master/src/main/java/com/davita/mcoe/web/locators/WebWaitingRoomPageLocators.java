package com.davita.mcoe.web.locators;

import org.openqa.selenium.By;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public final class WebWaitingRoomPageLocators {

	public static final By BYCHAT = By.xpath("//div[@class='col-xs-2 davita-contact']/a[1]/i");
	public static final By BYPATIENTNAME = By.cssSelector(".davita-patient-name");
	public static final By BYWAITINGROOMFRAME = By.id("vseeWaitingRoomFrame");
	public static final By BYWAITINGROOMCOMPFRAME = By.id("vseeCompFrame");
	public static final By BYCHATTEXTBOX = By.cssSelector("#webchat-input-form>input");
	public static final By BYCHATMESSAGES = By.className("webchat-message-content");
	public static final By BYNEXTSTEP = By.linkText("Next Step");
	public static final By BYENDSESSION = By.linkText("End this session");
}
