package com.davita.mcoe.web.pages;

import static com.davita.mcoe.web.locators.WebWaitingRoomPageLocators.*;

import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.davita.mcoe.exceptions.WaitException;
import com.davita.mcoe.page.base.WebPageBase;

/*  
 * @author Rishi Khanna
 * @version 2.0
 * @Team:DaVita MCOE
 * @Email:rishi.khanna@davita.com
 * @Company:CitiusTech
 */
public class WebWaitingRoomPage extends WebPageBase {

	public WebWaitingRoomPage(WebDriver driver) throws WaitException {
		super(driver);
		// Assert.assertTrue(webActions
		// .getVisibiltyOfElementLocatedBy(BYPATIENTNAME));
	}

	public WebWaitingRoomPage clickWaitingPatientForChat() throws TimeoutException,
			WaitException, InterruptedException {
		webActions.switchToFrame(VISIBILITY, BYWAITINGROOMFRAME);
		webActions.timedClick(VISIBILITY, BYCHAT, 4000);
		webActions.switchToParentFrame();
		return this;
	}

	public WebWaitingRoomPage sendMessageToPatient(String message)
			throws TimeoutException, WaitException, InterruptedException {
		webActions.switchToFrame(PRESENCE, BYWAITINGROOMCOMPFRAME);
		webActions.enterText(VISIBILITY, BYCHATTEXTBOX, message);
		webActions.enterKey(VISIBILITY, BYCHATTEXTBOX, Keys.RETURN);
		webActions.switchToParentFrame();
		return this;
	}

	public boolean verifyEnteredMessageIsDisplayed(String message) throws TimeoutException,
			WaitException {
		webActions.switchToFrame(VISIBILITY, BYWAITINGROOMCOMPFRAME);
		List<String> chatList = webActions.getWebElementsTextInList(VISIBILITY, BYCHATMESSAGES);
		webActions.switchToParentFrame();
		return COMPARE.compareExactText(chatList.get(chatList.size() - 1), message);
	}

	public WebWaitingRoomPage clickNextStep() throws TimeoutException, WaitException {
		webActions.switchToFrame(VISIBILITY, BYWAITINGROOMCOMPFRAME);
		webActions.click(VISIBILITY, BYNEXTSTEP);
		webActions.switchToParentFrame();
		return this;
	}

	public WebWaitingRoomPage clickEndSession() throws TimeoutException, WaitException {
		webActions.switchToFrame(VISIBILITY, BYWAITINGROOMCOMPFRAME);
		webActions.click(VISIBILITY, BYENDSESSION);
		webActions.switchToParentFrame();
		return this;
	}
}